package com.jd.test.alg;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by xueyue1 on 2018/5/17.
 */
public class BM25 {
    /**
     * 文档数
     */
    int D;

    /**
     * 文档的平均长度（所有分词的个数/所有文档）
     */
    double avgdl;

    /**
     * 拆分为[句子[单词]]形式的文档
     */
    List<List<String>> docs;

    /**
     * 文档中每个句子中的每个词与词频
     */
    Map<String, Integer>[] f;

        /**
         * 文档中中分词出现在全部文档的中的个数
         */
        Map<String, Integer> df;

    /**
     * IDF
     */
    Map<String, Double> idf;

    /**
     * 调节因子（正常为1.25-1.5）
     */
    final static float k1 = 1.25f;

    /**
     * 调节因子
     */
    final static float b = 0.75f;

    public BM25(List<List<String>> docs) {
        this.docs = docs;
        D = docs.size();
        for (List<String> sentence : docs) {
            avgdl += sentence.size();
        }
        avgdl /= D;
        f = new Map[D];
        df = new TreeMap<String, Integer>();
        idf = new TreeMap<String, Double>();
        init();
    }

    /**
     * 在构造时初始化自己的所有参数
     */
    private void init() {
        int index = 0;
        for (List<String> sentence : docs) {
            Map<String, Integer> tf = new TreeMap<String, Integer>();
            for (String word : sentence) {
                Integer freq = tf.get(word);
                freq = (freq == null ? 0 : freq) + 1;
                tf.put(word, freq);
            }
            f[index] = tf;
            for (Map.Entry<String, Integer> entry : tf.entrySet()) {
                String word = entry.getKey();
                Integer freq = df.get(word);
                freq = (freq == null ? 0 : freq) + 1;
                df.put(word, freq);
            }
            ++index;
        }
        for (Map.Entry<String, Integer> entry : df.entrySet()) {
            String word = entry.getKey();
            Integer freq = entry.getValue();
            //防止值为负数，所以+1
            idf.put(word, Math.log(1 + ((D - freq) + 0.5) / (freq + 0.5)));
        }
    }


    public String sim(List<String> query, List<String> doc) {

        Map<String, Integer> querytf = new TreeMap<String, Integer>();
        for (String word : query) {
            Integer freq = querytf.get(word);
            freq = (freq == null ? 0 : freq) + 1;
            querytf.put(word, freq);
        }

        Map<String, Integer> doctf = new TreeMap<String, Integer>();
        for (String word : doc) {
            Integer freq = doctf.get(word);
            freq = (freq == null ? 0 : freq) + 1;
            doctf.put(word, freq);
        }

        Map<String, String> map = Maps.newLinkedHashMap();

        StringBuilder sb = new StringBuilder();
        Map<String, BigDecimal> queryFreqMap = Maps.newLinkedHashMap();
        for (String word : query) {
            int wf = querytf.get(word);
            int termdoc = df.get(word);
            double idfv = idf.get(word);
            double score = idfv * wf * (k1 + 1) / (wf + k1 * (1 - b + b * query.size() / avgdl));
            queryFreqMap.put(word,new BigDecimal(score).setScale(10,BigDecimal.ROUND_HALF_DOWN));
        }

        BigDecimal totalScore1 = queryFreqMap.values().stream().reduce(BigDecimal.ZERO, (sum, item) -> sum.add(item));
        queryFreqMap.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> BigDecimal.valueOf(100).multiply(entry.getValue()).divide(totalScore1, 3, BigDecimal.ROUND_HALF_DOWN)));


        sb.append(queryFreqMap).append("#");

        Map<String, BigDecimal> otherFreqMap = Maps.newLinkedHashMap();
        for (String word : doc) {
            int wf = doctf.get(word);
            double score = (idf.get(word) * wf * (k1 + 1) / (wf + k1 * (1 - b + b * doc.size() / avgdl)));
            otherFreqMap.put(word,new BigDecimal(score).setScale(10,BigDecimal.ROUND_HALF_DOWN));
        }

        BigDecimal totalScore2 = otherFreqMap.values().stream().reduce(BigDecimal.ZERO, (sum, item) -> sum.add(item));
        otherFreqMap.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> BigDecimal.valueOf(100).multiply(entry.getValue()).divide(totalScore2, 3, BigDecimal.ROUND_HALF_DOWN)));

        sb.append(otherFreqMap).append("#");

        BigDecimal lawOfCosines = LawOfCosines.cal(queryFreqMap, otherFreqMap);
        sb.append("lawOfCosines").append(lawOfCosines.toPlainString());

        return sb.toString();
    }

    /*
    public Map sim(List<String> sentence, int index) {
        double totalScore = 0;

        Map<String,String> map = Maps.newLinkedHashMap();
        for (String word : sentence) {
            if (!f[index].containsKey(word)) {
                continue;
            }
            int d = docs.get(index).size();
            Integer wf = f[index].get(word);
            double score =  (idf.get(word) * wf * (k1 + 1) / (wf + k1 * (1 - b + b * d / avgdl)));
            totalScore = totalScore + score;

            double idfscore = idf.get(word);
            double tfNorm = wf * (k1 + 1) / (wf + k1 * (1 - b + b * d / avgdl));

            String plan = "idf:" + idfscore + ",tfNorm:"+tfNorm + ",score:" + score;

            map.put(word,plan);
        }
        map.put("totalScore",totalScore+"");

        return map;
    }


    public List<Map> simAll(List<String> sentence) {
        List<Map> list = Lists.newArrayList();
        for (int i = 0; i < D; ++i) {

            Map<String,String> map = sim(sentence, i);
            list.add(map);
        }
        return list;
    }
     */
}

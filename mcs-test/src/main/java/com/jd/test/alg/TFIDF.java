package com.jd.test.alg;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by xueyue1 on 2018/5/18.
 */
public class TFIDF {

    /**
     * 文档数
     */
    int docCount;

    /**
     * 文档中每个句子中的每个词与词频
     */
    Map<String, Integer>[] f;

    /**
     * IDF
     */
    Map<String, Double> idf;

    public TFIDF(List<List<String>> docs) {
        docCount = docs.size();
        Map<String, Integer> df = new TreeMap<String, Integer>();
        idf = new TreeMap<String, Double>();

        for (List<String> sentence : docs) {
            for(String word : sentence){
                Integer freq = df.get(word);
                freq = (freq == null ? 0 : freq) + 1;
                df.put(word, freq);
            }
        }
        for (Map.Entry<String, Integer> entry : df.entrySet()) {
            String word = entry.getKey();
            Integer freq = entry.getValue();
            //log(语料库中的文档总数/(包含该词的文档数+1))
            idf.put(word, Math.log(docCount) / (freq + 1));
        }
    }

    /**
     * 待比较的文档
     * @param docs
     * @return
     */
    public List<Map<String, BigDecimal>> sim(List<List<String>> docs) {
        List<Map<String, BigDecimal>> maps = Lists.newArrayList();

        for (List<String> doc : docs) {
            Map<String, BigDecimal> docMap = Maps.newLinkedHashMap();
            Map<String, Long> wordCountMap = doc.stream().collect(Collectors.groupingBy(text -> text, Collectors.counting()));

            for(String word : doc){
                Long wordCount = wordCountMap.get(word);
                BigDecimal tfidf = (BigDecimal.valueOf(wordCount).divide(BigDecimal.valueOf(wordCount),3)).multiply(BigDecimal.valueOf(idf.get(word)));
                docMap.put(word,tfidf);
            }

            BigDecimal totalScore = docMap.values().stream().reduce(BigDecimal.ZERO, (sum, item) -> sum.add(item));
            docMap.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> BigDecimal.valueOf(100).multiply(entry.getValue()).divide(totalScore, 3, BigDecimal.ROUND_HALF_DOWN)));
            maps.add(docMap);
        }
        return maps;
    }

    public static void main(String[] args) {
        BigDecimal bg=new BigDecimal("5.337817482448151E-4");
        System.out.println(bg);
    }

    /*
    public Map<String, Double> sim(List<String> sentence, int index) {
        double score = 0;
        Map<String, Double> maps = Maps.newHashMap();
        for (String word : sentence) {
            if (!f[index].containsKey(word)) {
                continue;
            }
            int d = docs.get(index).size();
            Integer wf = f[index].get(word);
            double tfidf = (wf / d) * idf.get(word);
            maps.put(word, tfidf);
            score = score + tfidf;
        }
        return maps;
    }
    */
}

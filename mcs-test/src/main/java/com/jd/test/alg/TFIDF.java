package com.jd.test.alg;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;

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

    Map<String, Integer> wordStatisticsCountMap = new TreeMap<String, Integer>();

    public TFIDF(List<List<String>> docs) {
        docCount = docs.size();
        for (List<String> sentence : docs) {
            for (String word : sentence) {
                Integer freq = wordStatisticsCountMap.get(word);
                freq = (freq == null ? 0 : freq) + 1;
                wordStatisticsCountMap.put(word, freq);
            }
        }
    }


    public Map<String, BigDecimal> sim(List<String> strWordsList) {
        //酒店总行数
        double doubleTotalSize = docCount;
        //当前字符串词组总数
        double strWordSize = strWordsList.size();
        //当前词组在字符串中出现的次数map
        Map<String, Long> strWordCountMap = strWordsList.stream()
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.groupingBy(text -> text, Collectors.counting()));
        return Sets.newHashSet(strWordsList).stream()
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toMap(word -> word, word -> {
                    //词组在分词统计中的酒店个数
                    int wordStatisticsCount = wordStatisticsCountMap.get(word) != null && wordStatisticsCountMap.get(word) > 0 ? wordStatisticsCountMap.get(word).intValue() : 0;
                    //词组在匹配字符串出现的次数
                    int strWordCount = strWordCountMap.get(word) != null ? strWordCountMap.get(word).intValue() : 0;
                    //w=该词在该酒店名中出现的次数/该酒店名的词总数*log(该城市的酒店总数/(该城市存在该词的酒店总数+1))
                    //doubleTotalSize / (wordStatisticsCount + 1) 小于 doubleTotalSize，不会越界
                    double weight = (strWordCount / strWordSize) * (Math.log(doubleTotalSize / (wordStatisticsCount + 1)));
                    return BigDecimal.valueOf(weight);
                }, (a, b) -> a));
    }

}

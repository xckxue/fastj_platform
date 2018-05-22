package com.jd.test.alg;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Created by xueyue1 on 2018/5/18.
 */
public class LawOfCosines {
    public static BigDecimal cal(Map<String, BigDecimal> queryFreqMap, Map<String, BigDecimal> otherFreqMap){
        Set<String> allWord = Sets.newHashSet();
        allWord.addAll(queryFreqMap.keySet());
        allWord.addAll(otherFreqMap.keySet());

        BigDecimal sum = allWord.stream()
                .map(word -> queryFreqMap.get(word) == null || otherFreqMap.get(word) == null ? BigDecimal.ZERO : queryFreqMap.get(word).multiply(otherFreqMap.get(word)))
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        BigDecimal sqrtA = BigDecimal.valueOf(Math.sqrt(queryFreqMap.values().stream()
                .filter(f -> f != null)
                .map(f -> BigDecimal.valueOf(Math.pow(f.doubleValue(), 2)))
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b)).doubleValue()));

        BigDecimal sqrtB = BigDecimal.valueOf(Math.sqrt(otherFreqMap.values().stream()
                .filter(f -> f != null)
                .map(f -> BigDecimal.valueOf(Math.pow(f.doubleValue(), 2)))
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b)).doubleValue()));

        if (BigDecimal.ZERO.compareTo(sqrtA) == 0 || BigDecimal.ZERO.compareTo(sqrtB) == 0) {
            return BigDecimal.ZERO;
        }

        return sum.divide(sqrtA.multiply(sqrtB), 10, BigDecimal.ROUND_HALF_DOWN);
    }
}

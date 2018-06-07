package com.jd.test.nlp;

import com.baidu.aip.nlp.AipNlp;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by xueyue1 on 2018/5/17.
 */
public class TestNlp {
    public static void main(String[] args) {
        AipNlp client = new AipNlp("11318392", "l3NjAVCA8B5yug3L0OW3o5GS", "VmrrNm3CdMo1GLwXDvW0ZONRruBqXhBF");

        String text1 = "青龙峡潞潞农家院";
        String text2 = "北京青龙峡农家院";

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "CNN");

        // 短文本相似度
        JSONObject res = client.simnet(text1, text2, options);
        System.out.println(res.toString(2));

        String text = "东三环南路 54号院";

        // 传入可选参数调用接口
        HashMap<String, Object> options1 = new HashMap<String, Object>();
        // 词法分析
        res = client.lexer(text, options1);
        System.out.println(res.toString(2));


        List<String> termList = HanLP.segment(text).stream().map(word -> word.word).collect(Collectors.toList());

        System.out.println(termList);

        System.out.println(ToAnalysis.parse(text));

    }
}

/**
 * 阻尼系数（ＤａｍｐｉｎｇＦａｃｔｏｒ），一般取值为0.85
 */
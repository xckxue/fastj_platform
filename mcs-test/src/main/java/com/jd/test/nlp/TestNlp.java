package com.jd.test.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * Created by xueyue1 on 2018/5/17.
 */
public class TestNlp {
    public static void main(String[] args) {

        List<Term> termList = HanLP.segment("广渠门外大街1号院富力城A1楼");
        System.out.println(termList);

    }
}

/**
 * 阻尼系数（ＤａｍｐｉｎｇＦａｃｔｏｒ），一般取值为0.85
 */
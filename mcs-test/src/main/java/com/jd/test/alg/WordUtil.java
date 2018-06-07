package com.jd.test.alg;

import org.apache.commons.lang.StringUtils;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.dictionary.DictionaryFactory;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.util.WordConfTools;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xueyue1 on 2018/5/25.
 */
public class WordUtil {
    static {
        //配置详见jar包下word.conf文件
        //不需要识别人名
        WordConfTools.set("person.name.recognize", "false");
        //dic.path配置词典路径，此处可能增加自己的词库
        WordConfTools.set("dic.path", "classpath:dic.txt,classpath:more.txt");
        //dic.class选择词典类型，因为需要动态增加或删除词，才选择org.apdplat.word.dictionary.impl.DictionaryTrie
        WordConfTools.set("dic.class", "org.apdplat.word.dictionary.impl.DictionaryTrie");
        //可以将词典最终的所有数据保存到dic.dump.path配置的文件中
        /*WordConfTools.set("dic.dump.path", "dump.txt");*/
        //更改词典配置之后，重新加载词典
        DictionaryFactory.reload();
        long end = System.currentTimeMillis();
    }

    public static List<String> splitStr2Words(String str) {
        if (StringUtils.isEmpty(str)) {
            return Collections.EMPTY_LIST;
        }
        return WordSegmenter.segWithStopWords(str, SegmentationAlgorithm.ReverseMaximumMatching).stream()
                .map(word -> word.getText())
                .collect(Collectors.toList());
    }
}

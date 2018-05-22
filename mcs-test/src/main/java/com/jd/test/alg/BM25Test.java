package com.jd.test.alg;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import io.netty.handler.codec.socks.SocksResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.xpath.SourceTree;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.Comparator.comparing;

/**
 * Created by xueyue1 on 2018/5/17.
 */
public class BM25Test {
    public static void main(String[] args) throws Exception {

        /*
        List<List<String>> alllist = new ArrayList<>();

        List<String> query = HanLP.segment("当下最火的女网红是谁").stream().map(word -> word.word).collect(Collectors.toList());

        List<String> termList2 = HanLP.segment("当下最火的男的明星为鹿晗").stream().map(word -> word.word).collect(Collectors.toList());
        List<String> termList3 = HanLP.segment("女网红能火的只是一小部分").stream().map(word -> word.word).collect(Collectors.toList());
        alllist.add(termList2);
        alllist.add(termList3);

        BM25 bm = new BM25(alllist);
        List<Map> list = bm.simAll(query);
        for(Map map : list){
            System.out.println(map);
        }
        */

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:/JDHotel.xls")));
        HSSFSheet sheet = null;
        sheet = workbook.getSheetAt(0);
        List<List<String>> alllist = new ArrayList<>();

        PrintWriter pw  = new PrintWriter(new File("e://bm25.txt"));

        for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
            HSSFRow row = sheet.getRow(j);
            String hotelId = getValue(row.getCell(0));
            String hotelName = getValue(row.getCell(1));
            List<String> termList = HanLP.segment(hotelName).stream().map(word -> word.word).collect(Collectors.toList());
            alllist.add(termList);
        }
        //初始化BM25
        BM25 bm = new BM25(alllist);

        //query词
        String testStr = "北京双龙快捷酒店";
        List<String> termList = HanLP.segment(testStr).stream().map(word -> word.word).collect(Collectors.toList());

        for(List<String> list : alllist){
            String msg = bm.sim(termList,list);
            pw.println(msg);
        }
        pw.flush();

        /*
        List<Map> list = bm.simAll(termList);
        for(Map map : list){
            BigDecimal lawOfCosines = LawOfCosines.cal(map,list.get(0));

            pw.println(lawOfCosines);
        }
        pw.flush();
        */

        /*
        Double[] scores = bm.simAll(termList);
        double max = Arrays.stream(scores).max(new Comparator<Double>() {
            @Override`
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        }).get();

        System.out.println(max);
        System.out.println("文档总数量"+alllist.size());
        int count = alllist.stream().map(list -> list.size()).reduce((a, b) -> a+b).get();
        System.out.println("文档分词总数量"+count);
        for(double d : scores){
            pw.println(d);
        }
        pw.flush();
        */

    }

    private static String getValue(HSSFCell hssfCell) {
        hssfCell.setCellType(CellType.STRING);
        return String.valueOf(hssfCell.getStringCellValue());
    }
}

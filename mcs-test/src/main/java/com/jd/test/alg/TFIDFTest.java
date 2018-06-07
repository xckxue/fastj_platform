package com.jd.test.alg;

import com.hankcs.hanlp.HanLP;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xueyue1 on 2018/5/18.
 */
public class TFIDFTest {
    public static void main(String[] args) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:/hotel/JDHotel.xls")));
        HSSFSheet sheet = null;
        sheet = workbook.getSheetAt(0);
        List<List<String>> alllist = new ArrayList<>();

        PrintWriter pw  = new PrintWriter(new File("e://hotel/tfidf.txt"));

        for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {
            HSSFRow row = sheet.getRow(j);
            String hotelId = getValue(row.getCell(0));
            String hotelName = getValue(row.getCell(1));
            List<String> termList = HanLP.segment(hotelName).stream().map(word -> word.word).collect(Collectors.toList());
            alllist.add(termList);
        }

        //初始化TF-IDF算法
        TFIDF tfidf = new TFIDF(alllist);

        //query词
        String testStr = "北京双龙酒店";
        List<String> termList = HanLP.segment(testStr).stream().map(word -> word.word).collect(Collectors.toList());
        Map<String,BigDecimal> queryFreqMap = tfidf.sim(termList);

        //待匹配文档的tfidf
        for(List<String> list : alllist){
            Map<String,BigDecimal> otherFreqMap = tfidf.sim(list);
            BigDecimal lawOfCosines = LawOfCosines.cal(queryFreqMap, otherFreqMap);

            StringBuilder sb = new StringBuilder("lawOfCosines:").append(lawOfCosines.toPlainString()).append("#");
            for (String key : otherFreqMap.keySet()) {
                sb.append(key).append(":").append(otherFreqMap.get(key)).append(";");
            }
            pw.println(sb.toString());
        }
        pw.flush();
    }

    private static String getValue(HSSFCell hssfCell) {
        hssfCell.setCellType(CellType.STRING);
        return String.valueOf(hssfCell.getStringCellValue());
    }
}

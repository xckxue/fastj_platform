package com.wow.test.jsoup;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.Base64;
import java.util.List;

/**
 * Created by wow on 2018/5/6.
 */
public class JsoupTest {
    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("f:/1.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        // 获取每一个工作薄
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);


        File file = new File("f:1.txt");
        PrintStream ps = new PrintStream(new FileOutputStream(file));


        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            String name = getValue(hssfRow.getCell(0));

            String url = "https://baike.baidu.com/item/"+name;
            Connection conn = Jsoup.connect(url);
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            Document document = conn.get();
            Elements elements = document.getElementsByClass("basicInfo-item");
            for (Element e : elements) {
                if ((e.text().indexOf("编") != -1) && (e.text().indexOf("剧") != -1) && e.hasClass("name")) {
                    System.out.println(name + "&&" + e.nextElementSibling().text());
                    ps.println(name + "&&" + e.nextElementSibling().text());
                }
            }
        }
    }

    // 转换数据格式
    private static String getValue(HSSFCell hssfCell) {
        hssfCell.setCellType(HSSFCell.CELL_TYPE_STRING);
        return String.valueOf(hssfCell.getStringCellValue());
    }
}

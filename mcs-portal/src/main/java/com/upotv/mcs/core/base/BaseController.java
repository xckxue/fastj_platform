package com.upotv.mcs.core.base;

import com.github.sd4324530.fastexcel.FastExcel;
import com.upotv.mcs.util.StringEscapeEditor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wow on 2017/7/22.
 */
public abstract class BaseController {

    @Value("${exportXls}")
    private String exportXls;

    public static final String PARAM = "params";

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
    }

    /**
     * redirect跳转
     *
     * @param url 目标url
     */
    protected String redirect(String url) {
        return new StringBuilder("redirect:").append(url).toString();
    }

    /**
     * 下载文件
     */
    protected <F> ResponseEntity<Resource> downloadXls(String fileName, List<F> list) throws Exception {
        String exportPath = exportXls + "/" + fileName + new Date().getTime() + ".xls";
        FastExcel fastExcel = new FastExcel(exportPath);
        fastExcel.createExcel(list);
        File file = new File(exportPath);
        return download(file, file.getName());
    }

    /**
     * 下载
     *
     * @param file     文件
     * @param fileName 生成的文件名
     * @return {ResponseEntity}
     */
    protected ResponseEntity<Resource> download(File file, String fileName) {
        Resource resource = new FileSystemResource(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent");
        // 避免空指针
        header = header == null ? "" : header.toUpperCase();

        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        HttpStatus status = HttpStatus.CREATED;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<Resource>(resource, headers, status);
    }
}


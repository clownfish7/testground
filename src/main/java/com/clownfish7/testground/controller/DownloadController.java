package com.clownfish7.testground.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author You
 * @create 2020-05-17 15:57
 */
@Controller
public class DownloadController {

    @RequestMapping(value = "/pdf", produces = "application/pdf")
    public void download(HttpServletResponse response, @RequestBody List<Map<String, Object>> list) throws IOException, DocumentException {
        //设置响应类型，这里是下载pdf文件
        response.setContentType("application/pdf");
        //设置Content-Disposition，设置attachment，浏览器会激活文件下载框；filename指定下载后默认保存的文件名
        //不设置Content-Disposition的话，文件会在浏览器内打卡，比如txt、img文件
        // 强制浏览器下载
//        response.addHeader("Content-Disposition", "attachment; filename=secret.pdf");
        // 浏览器尝试打开,支持office online或浏览器预览pdf功能
        response.addHeader("Content-Disposition", "inline; filename=secret.pdf");
        ServletOutputStream outputStream = response.getOutputStream();
//        File file = new File(pathh);
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.newPage();
        document.add(new Paragraph("1232"));
        document.close();
        outputStream.flush();
        System.out.println("over");
    }

}

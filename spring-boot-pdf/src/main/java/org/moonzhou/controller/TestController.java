package org.moonzhou.controller;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * http://localhost:8081/test/index
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "hello spring boot aspectJ demo project!!!";
    }

    /**
     * http://localhost:8081/test/generatePdf
     * @return
     */
    @RequestMapping("/generatePdf")
    public void generatePdf(HttpServletResponse response) {

        try {
            Map<String, String> params = new HashMap<>();
            params.put("applicant", "我爱罗");
            params.put("name", "lisi");
            params.put("applicantId", "666");
            params.put("email", "1@1.com");

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("application.pdf", "utf-8"));

            String resourcePath = this.getClass().getClassLoader().getResource("").getPath();
//            String templatePath = resourcePath + "/pdf/pdfTemplate.pdf";
            String fontPath = resourcePath + "pdf/font/Alibaba-PuHuiTi-Regular.ttf";

//            InputStream pdfTemplateInputStream = this.getClass().getClassLoader().getResourceAsStream("pdf/pdfTemplate.pdf");
            InputStream pdfTemplateInputStream = new ClassPathResource("pdf/pdfTemplate.pdf").getInputStream();
            OutputStream pdfOutputStream = response.getOutputStream();

            // 输出到固定地址
//            String newPDFPath = "/Users/xxxF/tmp/pdf/result.pdf";
//            PdfDocument pdf = new PdfDocument(new PdfReader(pdfTemplateInputStream), new PdfWriter(newPDFPath));

            // 通过流会写到浏览器
            PdfDocument pdf = new PdfDocument(new PdfReader(pdfTemplateInputStream), new PdfWriter(pdfOutputStream));

            /*PdfFont font = PdfFontFactory.createFont(this.getClass().getClassLoader().getResource("/").getPath()
                    + "font/Alibaba-PuHuiTi-Regular.ttf");*/
            PdfFont font = PdfFontFactory.createFont(fontPath);

            if (params != null && !params.isEmpty()) {
                // 有参数才替换
                PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
                Map<String, PdfFormField> fields = form.getFormFields();
                // 获取所有的表单域
                for (String param : params.keySet()) {
                    PdfFormField formField = fields.get(param);
                    // 获取某个表单域
                    if (formField != null) {
                        // 替换值
                        formField.setFont(font).setValue(params.get(param));
                        // 替换值
//                        formField.setValue(params.get(param));
                    }
                }
                // 锁定表单，不让修改
                form.flattenFields();
            }
            pdf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

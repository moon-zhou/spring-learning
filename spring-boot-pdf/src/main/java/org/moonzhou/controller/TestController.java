package org.moonzhou.controller;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.moonzhou.dto.TemplateParam;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/4/24 11:03
 **/
@Slf4j
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
     *
     * @return
     */
    @RequestMapping("/generatePdf")
    public void generatePdf(HttpServletResponse response) {

        // try with resources (AutoCloseable)
        try (InputStream pdfTemplateInputStream = new ClassPathResource("pdf/pdfTemplate.pdf").getInputStream();
             OutputStream pdfOutputStream = response.getOutputStream();
             PdfDocument pdf = new PdfDocument(new PdfReader(pdfTemplateInputStream), new PdfWriter(pdfOutputStream))) {
            Map<String, String> params = new HashMap<>();
            params.put("applicant", "我爱罗");
            params.put("name", "lisi");
            params.put("applicantId", "666");
            params.put("email", "1@1.com");

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("application.pdf", "utf-8"));

            String resourcePath = this.getClass().getClassLoader().getResource("").getPath();
//            String templatePath = resourcePath + "/pdf/pdfTemplate.pdf";
            String fontPath = resourcePath + "pdf/font/Alibaba-PuHuiTi-Regular.ttf";

//            InputStream pdfTemplateInputStream = this.getClass().getClassLoader().getResourceAsStream("pdf/pdfTemplate.pdf");


            // 输出到固定地址
//            String newPDFPath = "/Users/xxxF/tmp/pdf/result.pdf";
//            PdfDocument pdf = new PdfDocument(new PdfReader(pdfTemplateInputStream), new PdfWriter(newPDFPath));

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
//            try with resources, autoClosable
//            pdf.close();

        } catch (Exception e) {
            log.error("generatePdf error: ", e);
        }
    }


    /**
     * http://localhost:8081/test/exportPdf
     *
     * @return
     */
    @RequestMapping("/exportPdf")
    public void exportPdf(HttpServletResponse response) {

        // try with resources (AutoCloseable)
        try (InputStream pdfTemplateInputStream = new ClassPathResource("pdf/pdfTemplate.pdf").getInputStream();
             OutputStream pdfOutputStream = response.getOutputStream();
             PdfDocument pdf = new PdfDocument(new PdfReader(pdfTemplateInputStream), new PdfWriter(pdfOutputStream))) {

            TemplateParam templateParam = new TemplateParam(UUID.randomUUID().toString(), "Zhan San",
                    "001", "Li si", "1@1.com", "ABC", "officer",
                    "Zhang San", LocalDate.now(), "01", "01", "困难",
                    "支出费用10000", new BigDecimal(10000.01), "一次性", "困难原因");

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(templateParam.getApplicationFormCode() + ".pdf", "utf-8"));


            String resourcePath = this.getClass().getClassLoader().getResource("").getPath();
            String fontPath = resourcePath + "pdf/font/Alibaba-PuHuiTi-Regular.ttf";

            PdfFont font = PdfFontFactory.createFont(fontPath);

            // 有参数才替换
            if (templateParam != null) {
                // 获取所有的表单域
                PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
                Map<String, PdfFormField> fields = form.getFormFields();

                Field[] templateParamFields = TemplateParam.class.getDeclaredFields();
                for (Field templateParamField : templateParamFields) {
                    PdfFormField formField = fields.get(templateParamField.getName());
                    // 获取某个表单域
                    if (formField != null) {
                        templateParamField.setAccessible(true);
                        // 替换值
                        if (formField.getFieldName().getValue().equals("personnelClassification") || formField.getFieldName().getValue().equals("eventClassification")) {
                            // TODO 模板本身设置的值为Aa/Bb，但是读取过来是，为0/1
                            // 如果是通过itext7生成的pdf表单域，则可以读取的对应值且设置成功，后续需要分析关于表单域radio的设置值方式
                            formField.setValue("1");
                        } else {
                            formField.setFont(font).setValue(String.valueOf(templateParamField.get(templateParam)));
                        }
                    }
                }

                // 锁定表单，不让修改
                form.flattenFields();
            }
        } catch (Exception e) {
            log.error("export pdf error: ", e);
        }
    }
}

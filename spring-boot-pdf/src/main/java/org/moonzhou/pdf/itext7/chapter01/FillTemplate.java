package org.moonzhou.pdf.itext7.chapter01;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moon zhou
 * @description 表单域值填充，用于根据pdf模板，生成带有动态值的pdf文件，注意默认不支持中文，需要引入字体
 * @email ayimin1989@163.com
 * @date 2022/5/10 21:25
 **/
public class FillTemplate {

    public static void main(String[] args) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("applicant", "张三");
            params.put("name", "lisi");
            params.put("applicantId", "666");
            params.put("email", "1@1.com");

            String templatePath = "/Users/xxx/tmp/pdf/PDF 模板测试 1.pdf";
            String newPDFPath = "/Users/xxx/tmp/pdf/PDF 模板测试 2.pdf";
            String fontPath = "/Users/xxx/tmp/pdf/Alibaba-PuHuiTi-Regular.ttf";

            PdfDocument pdf = new PdfDocument(new PdfReader(templatePath), new PdfWriter(newPDFPath));

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

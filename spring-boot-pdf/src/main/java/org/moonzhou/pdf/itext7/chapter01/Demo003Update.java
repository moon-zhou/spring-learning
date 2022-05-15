package org.moonzhou.pdf.itext7.chapter01;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.util.Map;

/**
 * @author moon zhou
 * @description 读取之前生成的带有radio的pdf，重新给设置值
 * @email ayimin1989@163.com
 * @date 2022/5/13 17:49
 **/
public class Demo003Update {
    public static void main(String[] args) {
        String newPDFPath = "/Users/xxx/tmp/pdf/demo003-2.pdf";
        String fontPath = "/Users/xxx/tmp/pdf/Alibaba-PuHuiTi-Regular.ttf";

        try (PdfDocument pdf = new PdfDocument(new PdfReader(Demo003Generate.DEST), new PdfWriter(newPDFPath))) {

            PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
            Map<String, PdfFormField> fields = form.getFormFields();

            PdfFont font = PdfFontFactory.createFont(fontPath);

            PdfFormField nameField = fields.get("name");
            nameField.setValue("moon");

            PdfFormField languageField = fields.get("language");
//            languageField.setFont(font).setValue("English");
            languageField.setValue("English");

            // 锁定表单，不让修改
            form.flattenFields();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

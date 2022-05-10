package org.moonzhou.pdf.itext7.chapter01;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

/**
 * @author moon zhou
 * @description
 * @email ayimin1989@163.com
 * @date 2022/5/8 21:40
 **/
public class Demo001 {

    public static void main(String[] args) {
        try {
            String dest = "/Users/xxx/tmp/pdf/demo001.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph("Hello World!"));
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

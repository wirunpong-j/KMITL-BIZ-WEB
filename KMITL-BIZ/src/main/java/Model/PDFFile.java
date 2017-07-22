/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BellKunG
 */
public class PDFFile {
    
    public PDFFile() {
        
    }
    
    public void createPDF(String path) throws FileNotFoundException, IOException, BadElementException {
        //Initialize PDF writer
        
        PdfWriter writer = new PdfWriter(path);
 
        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
 
        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        document.setTopMargin(20);
        document.setBottomMargin(20);
        PdfFont font = PdfFontFactory.createFont("font/THSarabun.ttf", PdfEncodings.IDENTITY_H, true);
 
        for (int num = 1; num <= 2; num++) {
            Table table = new Table(10);
        
            Image img = new Image(ImageDataFactory.create("img/logo.png"));

            table.addCell(new Cell(1, 1).add(img.setWidth(50).setHeight(50)).setBorder(Border.NO_BORDER));
            table.addCell(createCell(1, 4, "KMITL BIZ WAY \n " +
                                           "3 ถนน ฉลองกรุง แขวง ลำปลาทิว \n " + 
                                           "เขต ลาดกระบัง กรุงเทพมหานคร 10520", "L", 12, 
                                           new float[] {0, 0, 0, 0}, 
                                           new float[] {2, 2, 2, 2}));
            
            String type = (num == 1) ? "ต้นฉบับ" : "สำเนา";
            table.addCell(createCell(1, 5, "ใบสำคัญจ่าย \n " + 
                                           "Payment Voucher \n " + 
                                           type, "R", 12, 
                                           new float[] {0, 0, 0, 0}, 
                                           new float[] {2, 2, 2, 2}));
            document.add(table);

            // ต้นฉบับ ตาราง 1
            Table table2 = new Table(10);
            table2.setMarginTop(5);

            table2.addCell(createCell(1, 1, "รหัส", "C", 12, new float[] {1, 0, 0, 1}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 6, "000001", "C", 12, new float[] {1, 1, 0, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 3, "วันที่", "C", 12, new float[] {1, 1, 0.3f, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 1, "จ่ายให้", "C", 12, new float[] {0, 0, 0, 1}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 6, "นาย วิรุฬพงศ์ ใจงามเลิศวงศ์", "C", 12, new float[] {0, 1, 0, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 3, "22 กรกฏาคม 2560", "C", 12, new float[] {0, 1, 1, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 1, "ที่อยู่", "C", 12, new float[] {0, 0, 0, 1}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 6, "112/39 ประชาสุขคอนโด", "C", 12, new float[] {0, 1, 0, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 3, "เลขที่ใบสำคัญจ่าย", "C", 12, new float[] {0, 1, 0.3f, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 1, "", "C", 12, new float[] {0, 0, 1, 1}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 6, "", "C", 12, new float[] {0, 1, 1, 0}, new float[] {1, 1, 1, 1}));
            table2.addCell(createCell(1, 3, "XXXXXX", "C", 12, new float[] {0, 1, 1f, 0}, new float[] {1, 1, 1, 1}));
            document.add(table2);

            // ต้นฉบับ ตาราง 2
            Table table3 = new Table(10);
            table3.setMarginTop(5);

            table3.addCell(createCell(1, 1, "ลำดับที่ / No.", "C", 12, new float[] {1, 0.3f, 0.3f, 1}, new float[] {1, 1, 1, 1}));
            table3.addCell(createCell(1, 5, "รายการ", "C", 12, new float[] {1, 0.3f, 0.3f, 0}, new float[] {1, 1, 1, 1}));
            table3.addCell(createCell(1, 2, "ตามบิลเลขที่", "C", 12, new float[] {1, 0.3f, 0.3f, 0}, new float[] {1, 1, 1, 1}));
            table3.addCell(createCell(1, 2, "จำนวนเงิน", "C", 12, new float[] {1, 1, 0.3f, 0}, new float[] {1, 1, 1, 1}));

            for (int i = 1; i <= 5; i++) {
                table3.addCell(createCell(1, 1, i + "", "C", 12, new float[] {0, 0.3f, 0.3f, 1}, new float[] {1, 1, 1, 1}));
                table3.addCell(createCell(1, 5, "A" + i, "C", 12, new float[] {0, 0.3f, 0.3f, 0}, new float[] {1, 1, 1, 1}));
                table3.addCell(createCell(1, 2, "B" + i, "C", 12, new float[] {0, 0.3f, 0.3f, 0}, new float[] {1, 1, 1, 1}));
                table3.addCell(createCell(1, 2, "C" + i, "C", 12, new float[] {0, 1, 0.3f, 0}, new float[] {1, 1, 1, 1}));
            }

            table3.addCell(createCell(1, 6, "จำนวนเงิน (บาท)", "L", 12, new float[] {1, 0, 0, 1}, new float[] {1, 2, 1, 5}));
            table3.addCell(createCell(1, 2, "ยอดรวมสุทธิ", "L", 12, new float[] {1, 0.3f, 0, 0}, new float[] {1, 2, 1, 2}));
            table3.addCell(createCell(2, 2, "3000", "C", 12, new float[] {1, 1, 1, 0}, new float[] {2, 2, 2, 2}).setVerticalAlignment(VerticalAlignment.MIDDLE));
            table3.addCell(createCell(1, 6, "สามพันบาทถ้วน", "L", 12, new float[] {0, 0, 1, 1}, new float[] {0, 2, 1, 5}));
            table3.addCell(createCell(1, 2, "Net Total", "L", 12, new float[] {0, 0.3f, 1, 0}, new float[] {0, 2, 1, 2}));

            document.add(table3);

            // ต้นฉบับ ตาราง 3
            Table table4 = new Table(10);
            table4.setMarginTop(5);

            table4.addCell(createCell(1, 3, "ผู้รับเงิน", "C", 12, new float[] {1, 1, 0, 1}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 1, "", "C", 12, new float[] {0, 0, 0, 0}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 3, "ผู้ตรวจสอบ", "C", 12, new float[] {1, 1, 0, 1}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 3, "ผู้อนุมัติ", "C", 12, new float[] {1, 1, 0, 1}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 3, "", "C", 12, new float[] {0, 1, 1, 1}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 1, "", "C", 12, new float[] {0, 0, 0, 0}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 3, "", "C", 12, new float[] {0, 1, 1, 1}, new float[] {1, 1, 1, 1}));
            table4.addCell(createCell(1, 3, "", "C", 12, new float[] {0, 1, 1, 1}, new float[] {1, 1, 1, 1}));
            
            document.add(table4);
            
            if (num == 1) {
                document.add(new Paragraph("----------------------------------------------------------------------------------------- ฉีกตามรอยปะ -----------------------------------------------------------------------------------------")
                        .setFont(font).setMargins(10, 5, 10, 5));
            }
        }
        
        
        //Close document
        document.close();
    }
    
    private Cell createCell(int col, int row, String text, String align, int size, float[] border_size, float[] padding) {
        PdfFont font = null;
        Cell cell = null;
        try {
            font = PdfFontFactory.createFont("font/THSarabun.ttf", PdfEncodings.IDENTITY_H, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Paragraph p = new Paragraph(text).setFont(font).setFontSize(size).setFixedLeading(15)
                .setPaddings(padding[0], padding[1], padding[2], padding[3]);
        
        switch (align) {
            case "L" : cell = new Cell(col, row).add(p).setTextAlignment(TextAlignment.LEFT); break;
            case "C" : cell = new Cell(col, row).add(p).setTextAlignment(TextAlignment.CENTER); break;
            case "R" : cell = new Cell(col, row).add(p).setTextAlignment(TextAlignment.RIGHT); break;
        }
        
        cell = (border_size[0] == 0) ? cell.setBorderTop(Border.NO_BORDER) : cell.setBorderTop(new SolidBorder(border_size[0]));
        cell = (border_size[1] == 0) ? cell.setBorderRight(Border.NO_BORDER) : cell.setBorderRight(new SolidBorder(border_size[1]));
        cell = (border_size[2] == 0) ? cell.setBorderBottom(Border.NO_BORDER) : cell.setBorderBottom(new SolidBorder(border_size[2]));
        cell = (border_size[3] == 0) ? cell.setBorderLeft(Border.NO_BORDER) : cell.setBorderLeft(new SolidBorder(border_size[3]));
        
        return cell;
    }
    
}

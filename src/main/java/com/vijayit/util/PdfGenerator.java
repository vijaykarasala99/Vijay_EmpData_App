package com.vijayit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vijayit.entity.EmpEntity;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class PdfGenerator {
	public void generate(HttpServletResponse response , List<EmpEntity> plans, File f) throws Exception{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance((com.lowagie.text.Document) document, response.getOutputStream());
		PdfWriter.getInstance((com.lowagie.text.Document) document, new FileOutputStream(f));

		document.open();
	
		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("WELCOME TO VIJAY INFRASTRUCTURE PVT.LTD", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);		
			
Paragraph p=new Paragraph ("Emp Details Info");
Paragraph p1=new Paragraph (" ");
document.add(p);
document.add(p1);
PdfPTable table =new PdfPTable(4);
table.setSpacingAfter(10);
table.addCell("Id");
table.addCell("Name");
table.addCell("Email");
table.addCell("Salary");

for(EmpEntity plan:plans) {
	 table.addCell(String.valueOf(plan.getId()));
	 table.addCell(plan.getName());
	 table.addCell(plan.getMail());
	 table.addCell(String.valueOf(plan.getSalary()));
 }
document.add(table);
document.close();
}
}
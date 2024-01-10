package com.vijayit.util;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.springframework.stereotype.Component;
import com.vijayit.entity.EmpEntity;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
@Component
public class ExcelGenerator {
public void generate(HttpServletResponse response, List<EmpEntity> records, File file) throws Exception {

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("emp-data");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Mail");
		headerRow.createCell(3).setCellValue("Salary");

		int dataRowIndex = 1;

		for (EmpEntity plan : records) {
			
			Row dataRow = sheet.createRow(1);
			dataRow.createCell(0).setCellValue(plan.getId());
			dataRow.createCell(1).setCellValue(plan.getName());
			dataRow.createCell(2).setCellValue(plan.getMail());
			dataRow.createCell(3).setCellValue(plan.getSalary());
			dataRowIndex++;
		}
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();

		ServletOutputStream outputstream = response.getOutputStream();
		workbook.write(outputstream);
		workbook.close();

	}
}
package com.excel.ExcelClass;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excel.model.Employee;

public class ExcelExport {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public List<Employee> empolyee2;

	public ExcelExport( List<Employee> empolyee2) {
		super();
		this.workbook = new XSSFWorkbook();
		this.empolyee2 = empolyee2;
	}

	
	public ExcelExport() {
		super();
		// TODO Auto-generated constructor stub
	}


	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);

	}

	private void WriteHeader() {
		sheet = workbook.createSheet("Employee");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row, 0, "Employee information", style);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		font.setFontHeightInPoints((short) (10));
		row = sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(18);
		style.setFont(font);
		createCell(row, 0, "ID", style);
		createCell(row, 1, "NAME", style);
		createCell(row, 2, "EMAIL", style);
		createCell(row, 3, "DEPARTMENT", style);
		createCell(row, 4, "SALARY", style);
	}

	private void writeLine() {
		int rowCount=2;
		
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for(Employee emp:empolyee2) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			createCell(row, columnCount++, emp.getId(), style);
			createCell(row, columnCount++, emp.getName(), style);
			createCell(row, columnCount++, emp.getEmail(), style);
			createCell(row, columnCount++, emp.getDepartment(), style);
			createCell(row, columnCount++, emp.getSalary(), style);
		}
	}
	
		public void export(HttpServletResponse reponse)throws IOException {
			WriteHeader();
			writeLine();
			ServletOutputStream sout=reponse.getOutputStream();
			workbook.write(sout);
			workbook.close();
			sout.close();
			
			
			
		
	}
}

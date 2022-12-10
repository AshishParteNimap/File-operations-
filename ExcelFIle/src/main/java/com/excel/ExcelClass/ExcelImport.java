package com.excel.ExcelClass;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excel.model.Employee;

public class ExcelImport {
	public List<Employee> ExcelImport() {
		List<Employee> emp = new ArrayList<>();
		long id = 0;
		String name = "";
		String email = "";
		String departemnt = "";
		int salary = 0;
		String filePath = "C:\\Users\\Nimap\\Documents\\ExcelImports\\Employee.xlsx";
		long start = System.currentTimeMillis();
		FileInputStream fileinput;
		try {
			fileinput = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fileinput);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						id = (long) nextCell.getNumericCellValue();
						System.out.println(id);
						break;
					case 1:
						name = nextCell.getStringCellValue();
						System.out.println(name);
						break;
					case 2:
						email = nextCell.getStringCellValue();
						System.out.println(email);
						break;
					case 3:
						departemnt = nextCell.getStringCellValue();
						System.out.println(departemnt);
						break;

					case 4:
						salary = (int) nextCell.getNumericCellValue();
						System.out.println(salary);
						break;
					}
					emp.add(new Employee(id, name, email, departemnt, salary));

				}
			}
			workbook.close();
			long end = System.currentTimeMillis();
			System.out.printf("import done", (end - start));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

}

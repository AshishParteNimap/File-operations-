package com.excel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.excel.ExcelClass.ExcelExport;
import com.excel.ExcelClass.ExcelImport;
import com.excel.model.Employee;
import com.excel.repo.EmployeeRepo;

@Controller
public class ExcelController {

	@Autowired
	private EmployeeRepo repo;

	@GetMapping("/getExcel")
	public String exportExcel(HttpServletResponse response)throws IOException{
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=Employee.xlsx";
		response.setHeader(headerKey, headerValue);
		List<Employee> employees=repo.findAll();
		ExcelExport Exp=new ExcelExport(employees);
		Exp.export(response);
		return "coverted successfuly";
	}
	
	@PostMapping("/addExcel")
	public String importExcel() {
		ExcelImport importE =new ExcelImport();
		List<Employee> employee=importE.ExcelImport();
		repo.saveAll(employee);
		return "done";
		
	}
	
}

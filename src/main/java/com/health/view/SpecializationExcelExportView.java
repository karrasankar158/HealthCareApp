package com.health.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.health.entity.Specialization;

public class SpecializationExcelExportView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String,Object> model,
			Workbook workbook,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//1. Define your own Excel file name
		response.addHeader("Content-Disposition", "attachment;filename=SPECIALIZATION.xls");//or
		//response.addHeader("Content-Disposition", "attachment;filename=SPEC.xlsx");

		//2. Read data given by Controller
		@SuppressWarnings("unchecked")
		List<Specialization> list=(List<Specialization>) model.get("list");
		model.get("list");

		//3. Create one Sheet
		Sheet sheet=workbook.createSheet("SPECIALIZATION");

		//4. create row#0 as header
		setHead(sheet);
		// 5. Create row#1 onwards from list<T> type 
		setBody(sheet,list);
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		//take based on entity class fields/properties/variables
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
	}
	private void  setBody(Sheet sheet,List<Specialization> list) {
		int rowNum=1;
		for(Specialization spec:list) {
			Row row=sheet.createRow(rowNum++);
			//take based on entity class fields/properties/variables
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}

	}

}

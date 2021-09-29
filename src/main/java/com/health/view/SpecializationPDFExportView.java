package com.health.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.health.entity.Specialization;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SpecializationPDFExportView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
			//PDF file creation
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		//1. Define your own PDF file name
		response.addHeader("Content-Disposition", "attachment;filename=SPECIALIZATION.pdf");

		//2. Read data given by Controller
		@SuppressWarnings("unchecked")
		List<Specialization> list=(List<Specialization>) model.get("list");
		model.get("list");

		//3.table with 4 column in a row , put our data in a table
		PdfPTable table = new PdfPTable(4);
		
		//table properties
		table.setWidthPercentage(60);
		table.setWidths(new int[] {1, 3, 4,6});
		
         // table header
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		//set color to header
		headFont.setColor(0,0,255);
		//column
		PdfPCell hcell;
       
		//ID header
		hcell=new PdfPCell(new Phrase("ID",headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		//CODE header
		hcell=new PdfPCell(new Phrase("CODE",headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		//NAME header
		hcell=new PdfPCell(new Phrase("NAME",headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		//NOTE header
		hcell=new PdfPCell(new Phrase("NOTE",headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		
         //loop for repeat all rows and adding into Document
		for(Specialization spec:list) {
			//column
			PdfPCell cell;

			//reading ID data
			cell=new PdfPCell(new Phrase(spec.getId().toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			//reading CODE data
			cell=new PdfPCell(new Phrase(spec.getSpecCode().toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			//reading NAME data
			cell=new PdfPCell(new Phrase(spec.getSpecName().toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			//reading NOTE data
			cell=new PdfPCell(new Phrase(spec.getSpecNote().toString()));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

		}
		
			document.add(table);
	}
}

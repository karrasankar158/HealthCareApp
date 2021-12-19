package com.health.view;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.health.entity.Specialization;
import com.health.service.ISpecializationService;

@Component
public class SpecializationCSVExportView {

	@Autowired
	private ISpecializationService service;

	@Autowired
	private HttpServletResponse response;

	public  void exportToCSV() {
		response.setContentType("text/csv");

		//CSV file name
		response.addHeader("Content-Disposition", "attachment;filename=SPECIALIZATION.csv");

		//Read data given by Controller
		List<Specialization> list=service.getAllSepcializations();

		try {
			ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

			String[] csvHeader = {"ID", "CODE", "NAME", "NOTE"};
			String[] nameMapping=new String[] {"id","specCode","specName","specNote"};

			csvWriter.writeHeader(csvHeader);

			for (Specialization spec : list) {
				csvWriter.write(spec, nameMapping);
			}

			csvWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

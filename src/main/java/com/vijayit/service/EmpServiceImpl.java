package com.vijayit.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vijayit.binding.SearchRequest;
import com.vijayit.entity.EmpEntity;
import com.vijayit.repo.EmpRepository;
import com.vijayit.util.EmailSender;
import com.vijayit.util.ExcelGenerator;
import com.vijayit.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpRepository empRepo;
	@Autowired
	private EmailSender emailUtils;
	@Autowired
	private ExcelGenerator excelGenerator;
	@Autowired
	private PdfGenerator pdfGenerator;

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File f = new File("Plans.xls");
		List<EmpEntity> records = empRepo.findAll();
		excelGenerator.generate(response, records, f);

		String subject ="Test Mail Subject";
		String body ="<h1>VIJAY INFRASTRUCTURE PVT.LTD</h1>";
		String to ="musunurinavya@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("Plans.pdf");
		List<EmpEntity> plans = empRepo.findAll();
		pdfGenerator.generate(response, plans, f);

		String subject = "Test Mail Subject";
		String body = "<h1>VIJAY INFRASTRUCTURE PVT.LTD</h1>";
		String to = "musunurinavya@gmail.com";
		emailUtils.sendEmail(subject, body, to, f);
		f.delete();
		return false;
	}

	@Override
	public List<String> getNames() {
		return empRepo.getNames();
	}

	@Override
	public List<String> getMails() {
		return empRepo.getMails();
	}

	@Override
	public List<String> getSalaries() {
		return empRepo.getSalaries();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<EmpEntity> search(SearchRequest request) {
		EmpEntity entity = new EmpEntity();
		if (null != request.getId() && !"".equals(request.getId())) {
			entity.setId(request.getId());
		}
		if (null != request.getName() && !"".equals(request.getName())) {
			entity.setName(request.getName());
		}
		if (null != request.getMail() && !"".equals(request.getMail())) {
			entity.setMail(request.getMail());
		}
		if (null != request.getSalary() && !"".equals(request.getSalary())) {
			entity.setSalary(request.getSalary());
		}

		return empRepo.findAll(Example.of(entity));
	}
}
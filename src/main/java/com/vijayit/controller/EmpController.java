package com.vijayit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vijayit.binding.SearchRequest;

import com.vijayit.entity.EmpEntity;
import com.vijayit.service.EmpService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;

	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition","attachment;filename=plans.pdf");
		empService.exportPdf(response);

	}

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment;filename=plans.xls");
		empService.exportExcel(response);

	}

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}

	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) {

		System.out.println(search);
		List<EmpEntity> plans = empService.search(search);
		model.addAttribute("plans", plans);
		init(model);
		return "index";
	}
  
    
	private void init(Model model) {
		
		model.addAttribute("names", empService.getNames());
		model.addAttribute("mails", empService.getMails());
		model.addAttribute("salaries", empService.getSalaries());
	}
}
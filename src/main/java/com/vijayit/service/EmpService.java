package com.vijayit.service;

import java.util.List;

import com.vijayit.binding.SearchRequest;
import com.vijayit.entity.EmpEntity;
import jakarta.servlet.http.HttpServletResponse;

public interface EmpService {

	public List<String> getNames();

	public List<String> getMails();

	public List<String> getSalaries();

	public List<EmpEntity> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response) throws Exception;

	public boolean exportPdf(HttpServletResponse response) throws Exception;

}

package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportSalesDTO;
import com.devsuperior.dsmeta.dto.SellerSalesSummary;
import com.devsuperior.dsmeta.dto.SellerSalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportSalesDTO>> getReport(@RequestParam(defaultValue = "") String name,
														  @RequestParam(required = false) String startDate,
														  @RequestParam(required = false) String endDate,
														  Pageable pageable) {
		Page<ReportSalesDTO> result = service.findReportSales(name, startDate, endDate, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerSalesSummaryDTO>> getSummary(@RequestParam(defaultValue = "") String name,
																  @RequestParam(required = false) String startDate,
																  @RequestParam(required = false) String endDate) {
		List<SellerSalesSummaryDTO> result = service.findSalesBySellerNameAndDateRange(name, startDate, endDate);
		return ResponseEntity.ok(result);
	}
}

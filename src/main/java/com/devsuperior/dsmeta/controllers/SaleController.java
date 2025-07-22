package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SellerSalesSummary;
import com.devsuperior.dsmeta.dto.SellerSalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<?> getReport() {
		// TODO
		return null;
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerSalesSummaryDTO>> getSummary(@RequestParam(defaultValue = "") String name,
																  @RequestParam(required = false) String startDate,
																  @RequestParam(required = false) String endDate) {
		List<SellerSalesSummaryDTO> result = service.findSalesBySellerNameAndDateRange(name, startDate, endDate);
		return ResponseEntity.ok(result);
	}
}

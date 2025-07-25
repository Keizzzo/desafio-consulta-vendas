package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<ReportSalesDTO> findReportSales(String name, String startDateStr, String endDateStr, Pageable pageable) {
		LocalDate endDate = (endDateStr != null && !endDateStr.isBlank())
				? LocalDate.parse(endDateStr)
				: LocalDate.now();

		LocalDate startDate = (startDateStr != null && !startDateStr.isBlank())
				? LocalDate.parse(startDateStr)
				: LocalDate.now().minusYears(1);

		// Se o nome estiver em branco, o filtro não deve restringir pelo nome
		Page<ReportSalesProjection> report =  repository.findReportSales(name, startDate, endDate, pageable);

		return report.map(s -> new ReportSalesDTO(s));
	}

	public List<SellerSalesSummaryDTO> findSalesBySellerNameAndDateRange(String name, String startDateStr, String endDateStr) {
		LocalDate endDate = (endDateStr != null && !endDateStr.isBlank())
				? LocalDate.parse(endDateStr)
				: LocalDate.now();

		LocalDate startDate = (startDateStr != null && !startDateStr.isBlank())
				? LocalDate.parse(startDateStr)
				: LocalDate.now().minusYears(1);

		// Se o nome estiver em branco, o filtro não deve restringir pelo nome
		List<SellerSalesSummary> summaries =  repository.findSalesBySellerNameAndDateRange(name, startDate, endDate);

		return summaries.stream().map(s -> new SellerSalesSummaryDTO(s)).toList();
	}
}

package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SellerSalesSummary;
import com.devsuperior.dsmeta.dto.SellerSalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
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

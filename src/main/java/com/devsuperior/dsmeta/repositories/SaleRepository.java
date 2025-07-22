package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SellerSalesSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = """
    SELECT seller.NAME, ROUND(SUM(sales.AMOUNT), 1) as total FROM TB_SALES AS sales
    INNER JOIN TB_SELLER AS seller ON sales.SELLER_ID = seller.ID
    WHERE LOWER(seller.NAME) LIKE LOWER(CONCAT('%', :name, '%'))
      AND sales.DATE BETWEEN :startDate AND :endDate
    GROUP BY seller.NAME
    """, nativeQuery = true)
    List<SellerSalesSummary> findSalesBySellerNameAndDateRange(
            @Param("name") String name,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}

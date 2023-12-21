package com.jose.apitestbwn.adapters.repositories;

import com.jose.apitestbwn.adapters.models.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    @Query(value = """
    select * from PRODUCTPRICE
    where BRAND_ID = :brandId and 
          PRODUCT_ID = :productId and
          :queryDate between START_DATE and END_DATE
    order by PRIORITY desc
    limit 1
    """, nativeQuery = true)
    Optional<PriceEntity> getPrice(int brandId, int productId, LocalDateTime queryDate);
}

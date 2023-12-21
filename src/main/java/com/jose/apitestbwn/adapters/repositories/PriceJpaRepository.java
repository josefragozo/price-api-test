package com.jose.apitestbwn.adapters.repositories;

import com.jose.apitestbwn.adapters.models.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> getByBrandIdAndProductId(int brandId, int productId);
}

package com.jose.apitestbwn.adapters.services;

import com.jose.apitestbwn.adapters.models.PriceEntity;
import com.jose.apitestbwn.adapters.repositories.PriceJpaRepository;
import com.jose.apitestbwn.application.adapters.PriceRepository;
import com.jose.apitestbwn.application.exceptions.PriceNotFoundException;
import com.jose.apitestbwn.domain.Price;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    @SneakyThrows
    public Price getPriceByBrandIdAndProductIdAndDate(
        int brandId,
        int productId,
        LocalDateTime date
    ) {
        final var price = priceJpaRepository.getPrice(brandId, productId, date);

        return priceOf(price.orElseThrow(PriceNotFoundException::new));
    }

    private Price priceOf(PriceEntity priceEntity) {
        return Price.builder()
            .id(priceEntity.getId())
            .brandId(priceEntity.getBrand().getId())
            .startDate(priceEntity.getStartDate())
            .endDate(priceEntity.getEndDate())
            .productId(priceEntity.getProductId())
            .priceList(priceEntity.getPriceList())
            .price(priceEntity.getPrice())
            .brandName(priceEntity.getBrand().getName())
            .priority(priceEntity.getPriority())
            .curr(priceEntity.getCurr())
            .build();
    }
}

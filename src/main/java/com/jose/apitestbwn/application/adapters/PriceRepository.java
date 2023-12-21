package com.jose.apitestbwn.application.adapters;

import com.jose.apitestbwn.domain.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price getPriceByBrandIdAndProductIdAndDate(
        int brandId,
        int productId,
        LocalDateTime date);
}

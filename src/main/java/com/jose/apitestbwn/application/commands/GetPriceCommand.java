package com.jose.apitestbwn.application.commands;

import com.jose.apitestbwn.adapters.dto.PriceResponse;
import com.jose.apitestbwn.application.adapters.PriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class GetPriceCommand {

    private final PriceRepository priceRepository;

    public PriceResponse execute(int brandId, int productId, LocalDateTime date){

        var price =  priceRepository.getPriceByBrandIdAndProductIdAndDate(
            brandId, productId, date
        );

        return PriceResponse.of(price, date);
    }
}

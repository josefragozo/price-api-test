package com.jose.apitestbwn.adapters.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jose.apitestbwn.domain.Price;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceResponse {

    // identificador de producto, identificador de cadena,
    // tarifa a aplicar, fechas de aplicación y precio final a aplicar.

    @JsonProperty("identificadorDeProducto")
    private int productId;

    @JsonProperty("cadena")
    private String brandName;

    @JsonProperty("tarifa")
    private double price;

    @JsonProperty("fechaDeAplicacion")
    private LocalDateTime queryDate;

    @JsonProperty("fechaFinalPrecio")
    private LocalDateTime endPriceDate;

    public static PriceResponse of(Price price, LocalDateTime queryDate) {
        return PriceResponse.builder()
            .productId(price.getProductId())
            .brandName(price.getBrandName())
            .price(price.getPrice())
            .queryDate(queryDate)
            .endPriceDate(price.getEndDate())
            .build();
    }
}

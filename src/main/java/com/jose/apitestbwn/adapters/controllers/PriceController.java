package com.jose.apitestbwn.adapters.controllers;

import com.jose.apitestbwn.adapters.dto.PriceResponse;
import com.jose.apitestbwn.application.commands.GetPriceCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.BRAND_ID;
import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.PRICE_CONTROLLER_NAME;
import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.PRODUCT_ID;
import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.QUERY_DATE;

@RestController
@AllArgsConstructor
@RequestMapping(PRICE_CONTROLLER_NAME)
public class PriceController {

    private final GetPriceCommand getPriceCommand;

    @Operation(summary = "Obtiene el precio de un producto para una fecha especifica")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "objeto con la informacion de precio e identificadores",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PriceResponse.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "retorna un mensaje de error al no encontra la informacion solicitada",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PriceResponse.class)
                )
            }
        )
    })
    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
        @RequestParam(BRAND_ID) int brandId,
        @RequestParam(PRODUCT_ID) int productId,
        @RequestParam(QUERY_DATE) LocalDateTime date
    ) {
        return ResponseEntity.ok(getPriceCommand.execute(brandId, productId, date));
    }
}

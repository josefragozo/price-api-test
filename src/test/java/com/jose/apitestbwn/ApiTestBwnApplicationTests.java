package com.jose.apitestbwn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jose.apitestbwn.adapters.controllers.PriceController;
import com.jose.apitestbwn.application.adapters.PriceRepository;
import com.jose.apitestbwn.application.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.BRAND_ID;
import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.PRICE_CONTROLLER_NAME;
import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.PRODUCT_ID;
import static com.jose.apitestbwn.adapters.configuration.constants.Pricing.QUERY_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiTestBwnApplicationTests {

    @Autowired
    private PriceController controller;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void Test1() throws Exception {
        mockMvc.perform(
                get(PRICE_CONTROLLER_NAME)
                    .param(BRAND_ID, "1")
                    .param(PRODUCT_ID, "35455")
                    .param(QUERY_DATE, "2020-06-14T10:00:00"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("{\"identificadorDeProducto\":35455,\"cadena\":\"ZARA\",\"tarifa\":35.5,\"fechaDeAplicacion\":\"2020-06-14T10:00:00\",\"fechaFinalPrecio\":\"2020-12-31T23:59:59\"}")));
    }

    @Test
    @DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void Test2() throws Exception {
        this.mockMvc.perform(
                get(PRICE_CONTROLLER_NAME)
                    .param(BRAND_ID, "1")
                    .param(PRODUCT_ID, "35455")
                    .param(QUERY_DATE, "2020-06-14T16:00:00"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("{\"identificadorDeProducto\":35455,\"cadena\":\"ZARA\",\"tarifa\":25.45,\"fechaDeAplicacion\":\"2020-06-14T16:00:00\",\"fechaFinalPrecio\":\"2020-06-14T18:30:00\"}")));
    }

    @Test
    @DisplayName("petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void Test3() throws Exception {
        this.mockMvc.perform(
                get(PRICE_CONTROLLER_NAME)
                    .param(BRAND_ID, "1")
                    .param(PRODUCT_ID, "35455")
                    .param(QUERY_DATE, "2020-06-14T21:00:00"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("{\"identificadorDeProducto\":35455,\"cadena\":\"ZARA\",\"tarifa\":35.5,\"fechaDeAplicacion\":\"2020-06-14T21:00:00\",\"fechaFinalPrecio\":\"2020-12-31T23:59:59\"}")));
    }

    @Test
    @DisplayName("petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void Test4() throws Exception {
        this.mockMvc.perform(
                get(PRICE_CONTROLLER_NAME)
                    .param(BRAND_ID, "1")
                    .param(PRODUCT_ID, "35455")
                    .param(QUERY_DATE, "2020-06-15T10:00:00"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("{\"identificadorDeProducto\":35455,\"cadena\":\"ZARA\",\"tarifa\":30.5,\"fechaDeAplicacion\":\"2020-06-15T10:00:00\",\"fechaFinalPrecio\":\"2020-06-15T11:00:00\"}")));
    }

    @Test
    @DisplayName("petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void Test5() throws Exception {
        this.mockMvc.perform(
                get(PRICE_CONTROLLER_NAME)
                    .param(BRAND_ID, "1")
                    .param(PRODUCT_ID, "35455")
                    .param(QUERY_DATE, "2020-06-16T21:00:00"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("{\"identificadorDeProducto\":35455,\"cadena\":\"ZARA\",\"tarifa\":38.95,\"fechaDeAplicacion\":\"2020-06-16T21:00:00\",\"fechaFinalPrecio\":\"2020-12-31T23:59:59\"}")));
    }

    @Test
    @DisplayName("precio no encontrado")
    void Test6() throws Exception {
        this.mockMvc.perform(
                get(PRICE_CONTROLLER_NAME)
                    .param(BRAND_ID, "1")
                    .param(PRODUCT_ID, "35455")
                    .param(QUERY_DATE, "2022-06-16T21:00:00"))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("Price Not Found")));
    }

    @Test()
    @DisplayName("precio no encontrado -> exception")
    public void Test7(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2022-06-16 21:00:00", formatter);

        assertThrows(PriceNotFoundException.class, () -> {
            priceRepository.getPriceByBrandIdAndProductIdAndDate(1, 35455, dateTime);
        });
    }
}

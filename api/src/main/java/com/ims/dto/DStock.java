package com.ims.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@Data
@AllArgsConstructor
public class DStock {
    private Long id;

    @NotNull(message = "the name of the stock should be provided")
    @NotBlank(message = "the name of the stock should be at least on character in length")
    private String name;

    @NotNull(message = "the status of the stock should be provided")
    @NotBlank(message = "the status of the stock should be at least on character in length")
    private String status;

    @NotNull(message = "the tags of the stock should be provided")
    @NotEmpty(message = "there should be at least 1 tag")
    @Size(min = 1, message = "there should be at least 1 tag")
    private Set<String> tags;

    @NotNull(message = "the quantity of the stock should be provided")
    @PositiveOrZero(message = "A valid quantity should be provided >= 0")
    private BigInteger availableQuantity;

    @NotNull(message = "the price of the stock should be provided")
    @PositiveOrZero(message = "A valid price should be provided >= 0")
    private BigDecimal price;
}

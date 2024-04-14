package com.ims.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class DNewStock {
    private String name;
    private String status;
    private Set<String> tags;
    private BigInteger availableQuantity;
    private BigDecimal price;
}

package com.ims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DWarehouse {
    private Long id;

    @NotNull(message = "the name of the warehouse should be provided")
    @NotBlank(message = "the name of the warehouse should be at least on character in length")
    private String name;

    @NotNull(message = "the location of the warehouse should be provided")
    @NotBlank(message = "the location of the warehouse should be at least on character in length")
    private String location;

    private Long manager_id;
}

package com.ims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DProvider {
    private Long id;

    @NotNull(message = "the name of the provider should be provided")
    @NotBlank(message = "the name of the provider should be at least on character in length")
    private String name;

    @NotNull(message = "the type of the provider should be provided")
    @NotBlank(message = "the type of the provider should be at least on character in length")
    private String type;
}

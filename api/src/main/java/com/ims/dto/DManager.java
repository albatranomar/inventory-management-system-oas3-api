package com.ims.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DManager {
    private Long id;

    @NotNull(message = "the name of the manager should be provided")
    @NotBlank(message = "the name of the manager should be at least on character in length")
    private String name;

    @NotNull(message = "the phone number of the manager should be provided")
    @NotBlank(message = "the phone number of the manager should be at least on character in length")
    private String phone_number;
}

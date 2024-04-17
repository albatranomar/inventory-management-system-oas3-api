package com.ims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DNewWarehouse {
    private String name;
    private String location;
    private Long manager_id;
}

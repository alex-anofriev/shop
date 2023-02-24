package com.shop.site.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Information about category sending from server to client")
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}

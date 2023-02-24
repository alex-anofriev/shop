package com.shop.site.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "information about product sending from server to client")
public class ProductResponseDto {
    private Long id;
    private String name;
    private List<Long> categoriesIds;
    private BigDecimal price;
    private double rating;
    private String description;
    private String productImage;
}

package com.shop.site.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "Information about product sending from client to server")
public class ProductRequestDto {
    @NotBlank
    @NotNull
    @Size(max = 255)
    @Schema(description = "The name of Product")
    private String name;
    @Schema(description = "The ids of categories which belong to product")
    private List<Long> categoriesIds;
    @Min(0)
    @Schema(description = "The price of product")
    private BigDecimal price;
    @Min(0)
    @Max(5)
    @Schema(description = "The rating of product")
    private double rating;
    @NotBlank
    @NotNull
    @Size(max = 1000)
    @Schema(description = "The description of product")
    private String description;
    @Schema(description = "Url of where stored image of product")
    private String productImage;
}

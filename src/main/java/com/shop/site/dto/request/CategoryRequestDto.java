package com.shop.site.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Information about category sending from client to server")
public class CategoryRequestDto {
    @NotBlank
    @NotNull
    @Size(max = 255)
    @Schema(description = "The name of category")
    private String name;
    @NotBlank
    @NotNull
    @Size(max = 1000)
    @Schema(description = "Description of category")
    private String description;
}

package com.shop.site.service.mapper;

import com.shop.site.dto.request.ProductRequestDto;
import com.shop.site.dto.response.ProductResponseDto;
import com.shop.site.model.Category;
import com.shop.site.model.Product;
import com.shop.site.service.CategoryService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RequestDtoMapper<ProductRequestDto, Product>,
        ResponseDtoMapper<ProductResponseDto, Product> {
    private CategoryService categoryService;

    public ProductMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Product mapToModel(ProductRequestDto requestDto) {
        Product product = new Product();
        product.setProductImage(requestDto.getProductImage());
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        product.setDescription(requestDto.getDescription());
        product.setRating(requestDto.getRating());
        product.setCategories(categoryService.findAllById(requestDto.getCategoriesIds()));
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product model) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(model.getId());
        responseDto.setProductImage(model.getProductImage());
        responseDto.setName(model.getName());
        responseDto.setPrice(model.getPrice());
        responseDto.setDescription(model.getDescription());
        responseDto.setRating(model.getRating());
        responseDto.setCategoriesIds(model.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}

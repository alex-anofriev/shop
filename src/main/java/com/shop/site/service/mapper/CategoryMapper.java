package com.shop.site.service.mapper;

import com.shop.site.dto.request.CategoryRequestDto;
import com.shop.site.dto.response.CategoryResponseDto;
import com.shop.site.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryRequestDto, Category>,
        ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public Category mapToModel(CategoryRequestDto requestDto) {
        Category category = new Category();
        category.setDescription(requestDto.getDescription());
        category.setName(requestDto.getName());
        return category;
    }

    @Override
    public CategoryResponseDto mapToDto(Category model) {
        CategoryResponseDto responseDto = new CategoryResponseDto();
        responseDto.setDescription(model.getDescription());
        responseDto.setName(model.getName());
        responseDto.setId(model.getId());
        return responseDto;
    }
}

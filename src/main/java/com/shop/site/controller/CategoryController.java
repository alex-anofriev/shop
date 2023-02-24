package com.shop.site.controller;

import com.shop.site.common.ApiResponse;
import com.shop.site.dto.request.CategoryRequestDto;
import com.shop.site.dto.response.CategoryResponseDto;
import com.shop.site.model.Category;
import com.shop.site.service.CategoryService;
import com.shop.site.service.mapper.CategoryMapper;
import com.shop.site.util.ParamSorterUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Methods to work with categories")
public class CategoryController {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private ParamSorterUtil paramSorterUtil;

    public CategoryController(CategoryService categoryService,
                              CategoryMapper categoryMapper,
                              ParamSorterUtil paramSorterUtil) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.paramSorterUtil = paramSorterUtil;
    }

    @PostMapping("/create")
    @Operation(summary = "Create new category")
    public ResponseEntity<ApiResponse> createCategory(
            @RequestBody @Valid CategoryRequestDto requestDto) {
        categoryService.save(categoryMapper.mapToModel(requestDto));
        return new ResponseEntity<>(new ApiResponse(
                true, "Category was created"), HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "Find category by its id")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryResponseDto responseDto = categoryMapper.mapToDto(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update/{categoryId}")
    @Operation(summary = "Update category")
    public ResponseEntity<ApiResponse> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid CategoryRequestDto requestDto) {
        if (!categoryService.isCategoryPresent(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(
                    false, "No category with id " + categoryId), HttpStatus.NOT_FOUND);
        }
        Category category = categoryMapper.mapToModel(requestDto);
        category.setId(categoryId);
        categoryService.update(category);
        return new ResponseEntity<>(new ApiResponse(
                true, "Category with id " + categoryId + " was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    @Operation(summary = "Delete category by its id")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        if (!categoryService.isCategoryPresent(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(
                    false, "No category with id " + categoryId), HttpStatus.NOT_FOUND);
        }
        categoryService.delete(categoryId);
        return new ResponseEntity<>(new ApiResponse(
                true, "Category with id " + categoryId + " was deleted"), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all existing categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.findAll();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}

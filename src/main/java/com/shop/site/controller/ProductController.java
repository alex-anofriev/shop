package com.shop.site.controller;

import com.shop.site.common.ApiResponse;
import com.shop.site.dto.request.ProductRequestDto;
import com.shop.site.dto.response.ProductResponseDto;
import com.shop.site.model.Product;
import com.shop.site.service.ProductService;
import com.shop.site.service.mapper.ProductMapper;
import com.shop.site.util.ParamSorterUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Methods to work with products")
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;
    private ParamSorterUtil paramSorterUtil;

    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             ParamSorterUtil paramSorterUtil) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.paramSorterUtil = paramSorterUtil;
    }

    @PostMapping("/create")
    @Operation(summary = "Create new product")
    public ResponseEntity<ApiResponse> createProduct(
            @RequestBody @Valid ProductRequestDto requestDto) {
        productService.save(productMapper.mapToModel(requestDto));
        return new ResponseEntity<>(new ApiResponse(
                true, "Product was created"), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Find product by its id")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long productId) {
        ProductResponseDto responseDto = productMapper.mapToDto(productService.findById(productId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    @Operation(summary = "Update product by id")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid ProductRequestDto requestDto) {
        if (!productService.isProductPresent(productId)) {
            return new ResponseEntity<>(new ApiResponse(
                    false, "No product with id: " + productId), HttpStatus.NOT_FOUND);
        }
        Product product = productMapper.mapToModel(requestDto);
        product.setId(productId);
        productService.save(product);
        return new ResponseEntity<>(new ApiResponse(
                true, "Product with id " + productId + " was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "Delete product by id")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        if (!productService.isProductPresent(productId)) {
            return new ResponseEntity<>(new ApiResponse(
                    false, "No product with id: " + productId), HttpStatus.NOT_FOUND);
        }
        productService.delete(productId);
        return new ResponseEntity<>(new ApiResponse(
                true, "Product with id " + productId + " was deleted"), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get existing products sorted and fixed amount")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = paramSorterUtil.parse(sortBy);
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        List<ProductResponseDto> responseDtos = productService.findAll(pageRequest)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}

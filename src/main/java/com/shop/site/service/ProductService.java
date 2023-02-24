package com.shop.site.service;

import com.shop.site.model.Product;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface ProductService extends CommonMethods<Product> {
    boolean isProductPresent(Long id);

    List<Product> findAll(PageRequest pageRequest);
}

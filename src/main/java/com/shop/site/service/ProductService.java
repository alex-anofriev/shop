package com.shop.site.service;

import com.shop.site.model.Product;

public interface ProductService extends CommonMethods<Product> {
    boolean isProductPresent(Long id);
}

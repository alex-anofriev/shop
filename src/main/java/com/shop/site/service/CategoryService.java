package com.shop.site.service;

import com.shop.site.model.Category;
import java.util.List;

public interface CategoryService extends CommonMethods<Category> {
    List<Category> findAllById(List<Long> ids);

    boolean isCategoryPresent(Long id);
}

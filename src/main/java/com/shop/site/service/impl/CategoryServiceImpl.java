package com.shop.site.service.impl;

import com.shop.site.exception.DataProcessingException;
import com.shop.site.model.Category;
import com.shop.site.repository.CategoryRepository;
import com.shop.site.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category entity) {
        categoryRepository.save(entity);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("No category with id: " + id));
    }

    @Override
    public void update(Category entity) {
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllById(List<Long> ids) {
        List<Category> allById = categoryRepository.findAllById(ids);
        if (allById.size() == 0) {
            throw new DataProcessingException("There are no valid categories");
        }
        return allById;
    }

    @Override
    public boolean isCategoryPresent(Long id) {
        return categoryRepository.findById(id).isPresent();
    }
}

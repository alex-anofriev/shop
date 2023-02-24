package com.shop.site.service;

import java.util.List;

public interface CommonMethods<T> {

    void save(T entity);

    T findById(Long id);

    void update(T entity);

    void delete(Long id);

    List<T> findAll();
}

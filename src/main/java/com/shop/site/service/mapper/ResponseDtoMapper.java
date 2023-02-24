package com.shop.site.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T model);
}

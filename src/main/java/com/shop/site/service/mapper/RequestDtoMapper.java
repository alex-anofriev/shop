package com.shop.site.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D requestDto);
}

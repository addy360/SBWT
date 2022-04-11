package com.addy360.sbjwt.services;

import java.util.List;

public interface CrudService<T> {
    List<T> index();
    T show(Long id);
    T store(T data);
    T update(T data);
}

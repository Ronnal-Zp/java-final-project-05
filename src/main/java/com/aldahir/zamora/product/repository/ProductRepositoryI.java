package com.aldahir.zamora.product.repository;

import com.aldahir.zamora.product.exception.InvalidProductException;
import com.aldahir.zamora.product.exception.NotFoundProductException;
import com.aldahir.zamora.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryI {
    List<Product> findAll() throws NotFoundProductException;

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    boolean existsById(Long id);

    void save (Product product);

    void update(Product product) throws NotFoundProductException;

    boolean delete(Long id);

}

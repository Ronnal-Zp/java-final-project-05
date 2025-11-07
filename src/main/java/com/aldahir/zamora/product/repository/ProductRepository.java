package com.aldahir.zamora.product.repository;

import com.aldahir.zamora.product.exception.NotFoundProductException;
import com.aldahir.zamora.product.interfaces.ProductRepositoryI;
import com.aldahir.zamora.product.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements ProductRepositoryI {

    private List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = new ArrayList<>();
    }


    @Override
    public List<Product> findAll() throws NotFoundProductException {
        if(products.isEmpty()) throw new NotFoundProductException("No existen productos.");
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return products.stream().filter(p -> p.getName().contains(name)).findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        return products.stream().anyMatch(product -> product.getId().equals(id));
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product updatedProduct) throws NotFoundProductException {
        int index = findIndexById(updatedProduct.getId());
        if(index < 0) throw new NotFoundProductException("No existe producto con id "+updatedProduct.getId());

        products.set(index, updatedProduct);
    }

    @Override
    public boolean delete(Long id) {
        return products.removeIf((product -> product.getId().equals(id)));
    }

    private int findIndexById(Long id) {
        for (int i=0; i<products.size(); i++) {
            if(products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}

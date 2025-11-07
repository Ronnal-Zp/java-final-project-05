package com.aldahir.zamora.product.service;

import com.aldahir.zamora.product.exception.InvalidProductException;
import com.aldahir.zamora.product.exception.NotFoundProductException;
import com.aldahir.zamora.product.interfaces.ProductRepositoryI;
import com.aldahir.zamora.product.model.Product;
import com.aldahir.zamora.product.model.ProductCategory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepositoryI productRepository;

    public ProductService(ProductRepositoryI productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(Optional<String> order) throws NotFoundProductException {
        List<Product> productList = this.productRepository.findAll();
        List<Product> sortedProduct;

        if(order.isPresent() && order.get().equals("PRICE")) {
            sortedProduct = productList.stream()
                    .sorted(Comparator.comparing(Product::getName)).toList();
        } else if (order.isPresent() && order.get().equals("NAME")) {
            sortedProduct = productList.stream()
                    .sorted(Comparator.comparingInt(p -> (int) p.getPrice())).toList();
        } else {
            sortedProduct = productList;
        }

        return sortedProduct;
    }

    public List<Product> filterByCategory(ProductCategory category) throws NotFoundProductException {
        List<Product> productList = this.productRepository.findAll();
        return productList.stream().filter(p -> p.getCategory().equals(category)).toList();
    }

    public List<Product> filterByPriceGrantherTo(Long priceGrantherTo) throws NotFoundProductException {
        List<Product> productList = this.productRepository.findAll();
        return productList.stream().filter(p -> p.getPrice() >= priceGrantherTo).toList();
    }

    public Product findById(Long id) throws NotFoundProductException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundProductException("No existe producto con id "+id));
    }

    public Product findByName(String name) throws NotFoundProductException {
        return productRepository.findByName(name).orElseThrow(() -> new NotFoundProductException("No existe producto con nombre "+name));
    }

    public void save(Product product) throws InvalidProductException {
        validateProduct(product, true);
        this.productRepository.save(product);
    }

    public void update(Product product) throws NotFoundProductException, InvalidProductException {
        validateProduct(product, false);
        this.productRepository.update(product);
    }

    public boolean delete(Long id) {
        return this.productRepository.delete(id);
    }

    private void validateProduct(Product product, boolean save) throws InvalidProductException {
        if(save && product.getId() <= 0) throw new InvalidProductException("El id es requerido");
        if(product == null) throw new InvalidProductException("Producto es null");
        if(product.getName().trim().isEmpty()) throw new InvalidProductException("El nombre es requerido");
        if(product.getPrice() < 0) throw new InvalidProductException("El precio no puede ser menor a 0");
        if(product.getStock() < 0) throw new InvalidProductException("El stock no puede ser menor a 0");
    }

}

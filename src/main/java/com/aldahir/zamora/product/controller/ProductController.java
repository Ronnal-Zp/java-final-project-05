package com.aldahir.zamora.product.controller;

import com.aldahir.zamora.product.exception.InvalidProductException;
import com.aldahir.zamora.product.exception.NotFoundProductException;
import com.aldahir.zamora.product.model.Product;
import com.aldahir.zamora.product.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void printProducts(Optional<String> order) {
        System.out.println("\n");
        try {
            List<Product> productList = productService.getAll(order);
            for (Product product: productList) {
                System.out.println("Producto: "+product.getName());
                System.out.println("Precio: "+product.getPrice());
                System.out.println("Stock: "+product.getStock());
                System.out.println();
            }
        } catch (NotFoundProductException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
            System.out.println("\n");
        }
    }

    public void printProductById(Long id) {
        System.out.println();
        try {
            Product p = productService.findById(id);
            printProduct(p);
        } catch (NotFoundProductException e) {
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println();
    }

    public void printProductByName(String name) {
        System.out.println();
        try {
            Product p = productService.findByName(name);
            printProduct(p);
        } catch (NotFoundProductException e) {
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println();
    }

    public Product getProductById(Long id) {
        try {
            Product p = productService.findById(id);
            return p;
        } catch (NotFoundProductException e) {
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public void save(Product product) {
        try {
            productService.save(product);
            System.out.println("Producto guardado con exito.");
        } catch (InvalidProductException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void update(Product product) {
        try {
            productService.update(product);
            System.out.println("Producto editado correctamente");
        } catch (NotFoundProductException | InvalidProductException e) {
            System.out.println("Error al editar producto: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        boolean isDeleted = false;
        System.out.println();
        try {
            isDeleted = productService.delete(id);

            if (isDeleted) {
                System.out.println("Producto eliminado correctamente");
            } else {
                System.out.println("No se puedo eliminar el producto");
            }
        } catch (InvalidProductException e) {
            System.out.println("Error: "+e.getMessage());
        }

        System.out.println();
    }

    private void printProduct(Product p) {
        System.out.println("Producto: "+p.getName());
        System.out.println("Precio: "+p.getPrice());
        System.out.println("Categoria: "+p.getCategory());
        System.out.println("Stock: "+p.getStock());
    }

}

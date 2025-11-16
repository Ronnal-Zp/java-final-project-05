package com.aldahir.zamora;

import com.aldahir.zamora.product.controller.ProductController;
import com.aldahir.zamora.product.repository.ProductRepository;
import com.aldahir.zamora.product.service.ProductService;
import com.aldahir.zamora.product.view.ProductView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        Scanner scanner = new Scanner(System.in);

        ProductView productView = new ProductView(productController,scanner);
        productView.start();

    }
}
package com.aldahir.zamora.product.view;

import com.aldahir.zamora.product.controller.ProductController;

import java.util.Optional;
import java.util.Scanner;

public class ProductView {

    final ProductController productController;
    final Scanner scanner;

    public ProductView(ProductController productController, Scanner scanner) {
        this.productController = productController;
        this.scanner = scanner;
    }

    public void start() {
        showMenu();
    }

    private void showMenu() {
        int opcion = 0;
        System.out.println("===Sistema de gestion de productos===");

        do {
            System.out.println("Seleccione una opcion");
            System.out.println("1. Listar productos");
            System.out.println("2. Guardar producto");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Buscar producto por ID");
            System.out.println("6. Buscar producto por nombre");
            opcion = scanner.nextInt();
            scanner.next();


            switch (opcion) {
                case 1:
                    printAllProducts();
                    break;

                default:
                    System.out.println("Opcion no valida");
                    opcion = 1;
                    break;
            }


        } while (opcion > 0);

        System.out.println("Saliendo del sistema...");
    }

    private void printAllProducts() {
        productController.printProducts(Optional.empty());
    }

    private void saveProduct() {

    }

    private void findProductById() {

    }

    private void findProductByName() {

    }

    private void deleteProduct() {

    }

}

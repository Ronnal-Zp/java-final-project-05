package com.aldahir.zamora.product.view;

import com.aldahir.zamora.product.controller.ProductController;
import com.aldahir.zamora.product.model.Product;
import com.aldahir.zamora.product.model.ProductCategory;

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


            switch (opcion) {
                case 1:
                    printAllProducts();
                    break;

                case 2:
                    saveProduct();
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
        System.out.println("Ingrese id:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Ingrese nombre:");
        String name = scanner.nextLine();

        System.out.println("Ingrese precio:");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese stock:");
        int stock = scanner.nextInt();
        scanner.nextLine();

        ProductCategory productCategory = questAndGetMenuCateory();

        productController.save(new Product(id,name,price,stock,productCategory));
        System.out.println();
    }

    private void findProductById() {

    }

    private void findProductByName() {

    }

    private void deleteProduct() {

    }

    private ProductCategory questAndGetMenuCateory() {
        ProductCategory productCategorySelected = null;
        System.out.println("Seleccione la categoria (Ingresar numero)");

        do {
            System.out.println("1. ELECTRONICOS");
            System.out.println("2. COMIDAS");
            System.out.println("3. LIBROS");
            System.out.println("4. OTROS");

            int categoryNumber = scanner.nextInt(); scanner.nextLine();

            switch (categoryNumber) {
                case 1:
                    productCategorySelected = ProductCategory.ELECTRONICOS;
                    break;

                case 2:
                    productCategorySelected = ProductCategory.COMIDAS;
                    break;

                case 3:
                    productCategorySelected = ProductCategory.LIBROS;
                    break;

                case 4:
                    productCategorySelected = ProductCategory.OTROS;
                    break;

                default:
                    System.out.println("No existe esa categoria, por favor escoga una de la lista");
                    break;
            }

        } while (productCategorySelected == null);

        return productCategorySelected;
    }

}

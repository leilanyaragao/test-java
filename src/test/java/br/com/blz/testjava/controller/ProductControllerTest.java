package br.com.blz.testjava.controller;

import br.com.blz.testjava.data.ProductData;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProductControllerTest {

    @BeforeEach
    void setUp() {
        productController.deleteAllProducts();

        Product product1 = ProductData.getProject();
        productController.createProduct(product1);

        Product product2 =  ProductData.getProject();
        product2.setSku(1);
        productController.createProduct(product2);
    }

    @Test
    void addWarehouse() {
        assertEquals(2,productController.getProductBySku(43264).getInventory().getWarehouses().size());
        productController.addWarehouse(43264, new Warehouse("RJ", 12, "TYPE"));
        assertEquals(3,productController.getProductBySku(43264).getInventory().getWarehouses().size());
        assertEquals(27, productController.getProductBySku(43264).getInventory().getQuantity());
    }

    @Test
    void createProduct() {
        assertNull(productController.getProductBySku(2));

        Product product = ProductData.getProject();
        product.setSku(2);

        productController.createProduct(product);

        assertNotNull(productController.getProductBySku(2));
    }

    @Test
    void deleteAllProducts() {
        assertEquals(2, productController.getAllProducts().size());
        productController.deleteAllProducts();
        assertEquals(0, productController.getAllProducts().size());
    }

    @Test
    void deleteProduct() {
        assertNotNull(productController.getProductBySku(43264));
        productController.deleteProduct(43264);
        assertNull(productController.getProductBySku(43264));
    }

    @Test
    void getAllProducts() {
        assertEquals(2, productController.getAllProducts().size());
    }

    @Test
    void getProductBySku() {
        assertNotNull(productController.getProductBySku(43264));
    }

    @Test
    void updateProduct() {
        assertEquals("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",productController.getProductBySku(43264).getName());

        Product updatedProduct =  ProductData.getProject();
        updatedProduct.setName("new name");

        productController.updateProduct(updatedProduct);

        assertEquals("new name", productController.getProductBySku(43264).getName());
    }

    @Autowired
    private ProductController productController;
}
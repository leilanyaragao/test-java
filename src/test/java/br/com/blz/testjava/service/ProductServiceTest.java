package br.com.blz.testjava.service;

import br.com.blz.testjava.data.ProductData;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProductServiceTest {

    @BeforeEach
    void setUp() {
        productService.deleteAllProducts();

        Product product1 = ProductData.getProject();
        productService.createProduct(product1);

        Product product2 =  ProductData.getProject();
        product2.setSku(1);
        productService.createProduct(product2);
    }

    @Test
    void addWarehouse() {
        assertEquals(2,productService.findProductBySku(43264).getInventory().getWarehouses().size());
        productService.addWarehouse(43264, new Warehouse("RJ", 12, "TYPE"));
        assertEquals(3,productService.findProductBySku(43264).getInventory().getWarehouses().size());
    }

    @Test
    void createProduct() {
        assertNull(productService.findProductBySku(2));

        Product product = ProductData.getProject();
        product.setSku(2);

        productService.createProduct(product);

        assertNotNull(productService.findProductBySku(2));
    }

    @Test
    void deleteAllProducts() {
        assertNotNull(productService.findAllProducts());
        productService.deleteAllProducts();
        assertEquals(new ArrayList<>() ,productService.findAllProducts());
    }

    @Test
    void deleteProductBySky() {
        assertNotNull(productService.findProductBySku(43264));
        productService.deleteProductBySky(43264);
        assertNull(productService.findProductBySku(43264));
    }

    @Test
    void findAllProducts() {
        assertEquals(2, productService.findAllProducts().size());
    }

    @Test
    void findProductBySku() {
        assertNotNull(productService.findProductBySku(43264));
    }

    @Test
    void updateProduct() {
        assertEquals("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", productService.findProductBySku(43264).getName());

        Product updatedProduct =  ProductData.getProject();
        updatedProduct.setName("new name");

        productService.updateProduct(updatedProduct);

        assertEquals("new name", productService.findProductBySku(43264).getName());
    }

    @Autowired
    private ProductService productService;
}
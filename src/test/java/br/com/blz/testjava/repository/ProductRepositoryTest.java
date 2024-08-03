package br.com.blz.testjava.repository;

import br.com.blz.testjava.data.ProductData;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
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
class ProductRepositoryTest {

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        Product product = ProductData.getProject();
        productRepository.save(product);
    }

    @Test
    void findProductBySku() {
        assertNotNull(productRepository.findProductBySku(43264));
        assertEquals(43264, productRepository.findProductBySku(43264).getSku());
    }

    @Test
    void deleteProductBySku() {
        assertNotNull(productRepository.findProductBySku(43264));
        productRepository.deleteProductBySku(43264);
        assertNull(productRepository.findProductBySku(43264));
    }

    @Autowired
    private ProductRepository productRepository;
}
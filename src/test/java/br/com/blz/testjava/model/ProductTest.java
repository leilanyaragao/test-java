package br.com.blz.testjava.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void createProductTest() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(new Warehouse("SP", 12, "ECOMMERCE"));
        warehouses.add(new Warehouse("MOEMA", 3, "PHYSICAL_STORE"));

        Inventory inventory = new Inventory(15, warehouses);

        Product product = new Product(43264, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", inventory);

        assertEquals(43264, product.getSku());
        assertEquals("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", product.getName());
        assertEquals(15, product.getInventory().getQuantity());
        assertTrue(product.isMarketable());
    }
}
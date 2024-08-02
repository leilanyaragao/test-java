package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    public Product createProduct(Product product) {
        if(productRepository.findProductBySku(product.getSku()) != null ){
            throw new ProductAlreadyExistsException("The product with sku " + product.getSku() + " already was registered.");
        }

        product.getInventory().setQuantity(getTotalInventoryQuantity(product));
        product.setMarketable(marketable(product.getInventory().getQuantity()));

        return productRepository.save(product);
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public void deleteProductBySky(Integer sku) {
        if(productRepository.findProductBySku(sku) == null ){
            throw new ProductNotFoundException("The product with sku " + sku + " not found.");
        }
        productRepository.deleteProductBySku(sku);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductBySku(Integer sku) {
        if(productRepository.findProductBySku(sku) == null ){
            throw new ProductNotFoundException("The product with sku " + sku + " not found.");
        }
        return productRepository.findProductBySku(sku);
    }

    public Product updateProduct(Product product) {
        Product productBySku = productRepository.findProductBySku(product.getSku());
        if(productBySku == null){
            throw new ProductNotFoundException("The product with sku " + product.getSku() + " not found.");
        }

        productBySku.setSku(product.getSku());
        productBySku.setName(product.getName());
        productBySku.setInventory(product.getInventory());
        productBySku.setMarketable(product.isMarketable());
        productBySku.getInventory().setQuantity(getTotalInventoryQuantity(product));
        productBySku.setMarketable(marketable(product.getInventory().getQuantity()));

        return productRepository.save(productBySku);
    }

    public int getTotalInventoryQuantity(Product product) {
        int totalQuantity = 0;
        for (Warehouse warehouse : product.getInventory().getWarehouses()) {
            totalQuantity += warehouse.getQuantity();
        }
        return totalQuantity;
    }

    public boolean marketable(int inventoryQuantity) {
        return inventoryQuantity > 0;
    }

    private final ProductRepository productRepository;
}

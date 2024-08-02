package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductBySku(Integer sku);

    void deleteProductBySku(Integer sku);
}

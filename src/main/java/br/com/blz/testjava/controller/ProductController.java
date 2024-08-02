package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    @DeleteMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer sku) {
         productService.deleteProductBySky(sku);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductBySku(@PathVariable Integer sku) {
        return productService.findProductBySku(sku);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    private final ProductService productService;

}

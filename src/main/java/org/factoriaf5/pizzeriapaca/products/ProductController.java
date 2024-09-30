package org.factoriaf5.pizzeriapaca.products;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${api-endpoint}/products") 
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all") 
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}") 
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create") 
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping(path = "/{id}") 
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        product.setId(id);
        Product updateProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping(path = "/{id}") 
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/type/{productType}") 
    public ResponseEntity<List<Product>> getProductByType(@PathVariable ProductType productType) {
        List<Product> products = productService.getProductsByType(productType);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/available") 
    public ResponseEntity<List<Product>> getAvailableProduct() {
        List<Product> availableProducts = productService.getAvailableProducts();
        return ResponseEntity.ok(availableProducts);
    }
}

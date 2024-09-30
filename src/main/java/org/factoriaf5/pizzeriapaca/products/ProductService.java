package org.factoriaf5.pizzeriapaca.products;

import java.util.List;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Product product) {
        if (!repository.existsById(product.getId())) {
            throw new ProductNotFoundException("Product not found");
        }
        return repository.save(product);
    }

    public void deleteProductById(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    public List<Product> getProductsByType(ProductType productType) { 
        return repository.findByProductType(productType); 
    }

    public List<Product> getAvailableProducts() {
        return repository.findByAvailableTrue();
    }
    
}
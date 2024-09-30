package org.factoriaf5.pizzeriapaca.products;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = Arrays.asList(
                new Product(1L, "Pizza Margherita", "Classic Margherita pizza", 8.99, ProductType.PIZZA, "image-url-1", true),
                new Product(2L, "Coca Cola", "Refreshing drink", 2.50, ProductType.BEBIDA, "image-url-2", true)
        );

        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductByIdSuccess() {
        Product mockProduct = new Product(1L, 
                                          "Pizza Pepperoni", 
                                          "Delicious pizza with pepperoni", 
                                          9.99, 
                                          ProductType.PIZZA, 
                                          "image-url", 
                                          true);
        when(productService.getProductById(1L)).thenReturn(mockProduct);

        ResponseEntity<Product> response = productController.getProductById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pizza Pepperoni", response.getBody().getName());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productService.getProductById(1L)).thenThrow(new ProductNotFoundException("Product not found"));

        ResponseEntity<Product> response = productController.getProductById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product(null, 
                                         "Pizza Pepperoni", 
                                         "Delicious pizza with pepperoni", 
                                         9.99, 
                                         ProductType.PIZZA, 
                                         "image-url", 
                                         true);
        Product createdProduct = new Product(1L, 
                                             "Pizza Pepperoni", 
                                             "Delicious pizza with pepperoni", 
                                             9.99, 
                                             ProductType.PIZZA, 
                                             "image-url", 
                                             true);

        when(productService.createProduct(newProduct)).thenReturn(createdProduct);

        ResponseEntity<Product> response = productController.createProduct(newProduct);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Pizza Pepperoni", response.getBody().getName());
        verify(productService, times(1)).createProduct(newProduct);
    }

    @Test
    public void testUpdateProductSuccess() {
        Product existingProduct = new Product(1L, 
                                              "Pizza Margherita", 
                                              "Classic Margherita pizza", 
                                              8.99, 
                                              ProductType.PIZZA, 
                                              "image-url", 
                                              true);
        when(productService.updateProduct(existingProduct)).thenReturn(existingProduct);

        ResponseEntity<Product> response = productController.updateProduct(existingProduct, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pizza Margherita", response.getBody().getName());
        verify(productService, times(1)).updateProduct(existingProduct);
    }

    @Test
    public void testDeleteProductSuccess() {
        doNothing().when(productService).deleteProductById(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(1L);
    }

    @Test
    public void testDeleteProductNotFound() {
        doThrow(new ProductNotFoundException("Product not found")).when(productService).deleteProductById(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(1L);
    }
}

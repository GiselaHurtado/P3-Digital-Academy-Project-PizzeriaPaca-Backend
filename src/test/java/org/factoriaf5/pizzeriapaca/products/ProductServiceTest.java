package org.factoriaf5.pizzeriapaca.products;

import org.factoriaf5.pizzeriapaca.products.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setProductType(ProductType.PIZZA);
        product.setImage("test_image.png");
        product.setAvailable(true);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(product, result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProduct(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(product));
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(0)).save(product);
    }

    @Test
    void testDeleteProductById() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProductById(1L);

        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProductByIdNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(1L));
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(0)).deleteById(1L);
    }

    @Test
    void testGetProductsByType() {
        List<Product> products = Arrays.asList(product);
        when(productRepository.findByProductType(ProductType.PIZZA)).thenReturn(products);

        List<Product> result = productService.getProductsByType(ProductType.PIZZA);

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(productRepository, times(1)).findByProductType(ProductType.PIZZA);
    }

    @Test
    void testGetAvailableProducts() {
        List<Product> availableProducts = Arrays.asList(product);
        when(productRepository.findByAvailableTrue()).thenReturn(availableProducts);

        List<Product> result = productService.getAvailableProducts();

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
        verify(productRepository, times(1)).findByAvailableTrue();
    }
}
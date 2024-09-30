package org.factoriaf5.pizzeriapaca.products;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Test Pizza", "Delicious cheese pizza", 9.99, ProductType.PIZZA, "test_image.jpg", true);
    }

    @Test
    void testSetAvailableTrue() {
        product.setAvailable(true);
        assertTrue(product.getAvailable(), "The product should be available after setting to true.");
    }

    @Test
    void testSetAvailableFalse() {
        product.setAvailable(false);
        assertFalse(product.getAvailable(), "The product should not be available after setting to false.");
    }

    @Test
    void testGetId() {
        assertEquals(1L, product.getId(), "The product ID should be 1.");
    }

    @Test
    void testSetId() {
        product.setId(2L);
        assertEquals(2L, product.getId(), "The product ID should be 2 after setting.");
    }

    @Test
    void testGetName() {
        assertEquals("Test Pizza", product.getName(), "The product name should be 'Test Pizza'.");
    }

    @Test
    void testSetName() {
        product.setName("New Pizza");
        assertEquals("New Pizza", product.getName(), "The product name should be 'New Pizza' after setting.");
    }

    @Test
    void testGetDescription() {
        assertEquals("Delicious cheese pizza", product.getDescription(), "The product description should be 'Delicious cheese pizza'.");
    }

    @Test
    void testSetDescription() {
        product.setDescription("New description");
        assertEquals("New description", product.getDescription(), "The product description should be 'New description' after setting.");
    }

    @Test
    void testGetPrice() {
        assertEquals(9.99, product.getPrice(), "The product price should be 9.99.");
    }

    @Test
    void testSetPrice() {
        product.setPrice(12.99);
        assertEquals(12.99, product.getPrice(), "The product price should be 12.99 after setting.");
    }

    @Test
    void testGetType() {
        assertEquals(ProductType.PIZZA, product.getProductType(), "The product type should be PIZZA.");
    }

    @Test
    void testSetProductType() {
        product.setProductType(ProductType.POSTRE);
        assertEquals(ProductType.POSTRE, product.getProductType(), "The product type should be POSTRE after setting.");
    }

    @Test
    void testGetImage() {
        assertEquals("test_image.jpg", product.getImage(), "The product image should be 'test_image.jpg'.");
    }

    @Test
    void testSetImage() {
        product.setImage("new_image.jpg");
        assertEquals("new_image.jpg", product.getImage(), "The product image should be 'new_image.jpg' after setting.");
    }

    @Test
    void testFullProductInitialization() {
        Product newProduct = new Product(2L, "Test Drink", "Refreshing drink", 2.99, ProductType.BEBIDA, "drink_image.jpg", true);
        assertEquals(2L, newProduct.getId(), "The product ID should be 2.");
        assertEquals("Test Drink", newProduct.getName(), "The product name should be 'Test Drink'.");
        assertEquals("Refreshing drink", newProduct.getDescription(), "The product description should be 'Refreshing drink'.");
        assertEquals(2.99, newProduct.getPrice(), "The product price should be 2.99.");
        assertEquals(ProductType.BEBIDA, newProduct.getProductType(), "The product type should be BEBIDA.");
        assertEquals("drink_image.jpg", newProduct.getImage(), "The product image should be 'drink_image.jpg'.");
    }
}

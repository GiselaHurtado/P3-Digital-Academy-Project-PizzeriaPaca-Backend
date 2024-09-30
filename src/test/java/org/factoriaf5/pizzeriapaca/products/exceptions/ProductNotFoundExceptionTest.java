package org.factoriaf5.pizzeriapaca.products.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductNotFoundExceptionTest {

    @Test
    public void testProductNotFoundExceptionWithMessageAndCause() {
        
        String errorMessage = "Product not found";
        Throwable cause = new RuntimeException("Cause of the error");

       
        ProductNotFoundException exception = new ProductNotFoundException(errorMessage, cause);

   
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}

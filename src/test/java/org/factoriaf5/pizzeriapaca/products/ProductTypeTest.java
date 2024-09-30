package org.factoriaf5.pizzeriapaca.products;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTypeTest {
    @Test
    void testValueOf() {
  assertEquals(ProductType.PIZZA, ProductType.valueOf("PIZZA"));
        assertEquals(ProductType.BEBIDA, ProductType.valueOf("BEBIDA"));
        assertEquals(ProductType.POSTRE, ProductType.valueOf("POSTRE"));
    }

    @Test
    void testValues() {
  ProductType[] expectedValues = {ProductType.PIZZA, ProductType.BEBIDA, ProductType.POSTRE};
        assertArrayEquals(expectedValues, ProductType.values());
    }
}

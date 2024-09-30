package org.factoriaf5.pizzeriapaca.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByAvailableTrue();

    List<Product> findByProductType(ProductType productType);

}
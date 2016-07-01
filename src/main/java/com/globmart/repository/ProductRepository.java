package com.globmart.repository;

import com.globmart.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
/**
 * Created by subramanian_p on 30-06-2016.
 */
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByProductName(String productName);

    List<Product> findByProductType(String productType);

    List<Product> findByProductBrand(String productBrand);

    List<Product> findAllByOrderByProductNameAsc();
}

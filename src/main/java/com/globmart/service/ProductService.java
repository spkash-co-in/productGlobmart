package com.globmart.service;

import com.globmart.domain.Product;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;
/**
 * Created by subramanian_p on 30-06-2016.
 */
public interface ProductService {
    @PreAuthorize("hasRole('ADMIN')")
    Product save(Product product);

    List<Product> getProductList();

    List<Product> getProductByName(String productName);

    List<Product> getProductByType(String productType);

    List<Product> getProductByBrand(String productBrand);

    Product getProductById(UUID id);
}

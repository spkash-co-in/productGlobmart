package com.globmart.service;

import com.globmart.domain.Product;
import com.globmart.repository.ProductRepository;
import com.globmart.service.exception.ProductAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
/**
 * Created by subramanian_p on 30-06-2016.
 */
@Service
@Validated
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional
    public Product save(@NotNull @Valid final Product product) {
        LOGGER.debug("Creating {}", product);

        UUID id = product.getId();
        if (id != null) {
            Product existing = productRepository.findOne(id);
            if (existing != null) {
                throw new ProductAlreadyExistsException(
                        String.format("There already exists a product with id=%s", id));
            }
        } else {
            if (productRepository.findByProductName(product.getProductName()).size()>0) {
                throw new ProductAlreadyExistsException(
                        String.format("There already exists a product with name=%s", product.getProductName()));
            }
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductList() {
        LOGGER.debug("Retrieving the list of all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByName(String productName) {
        LOGGER.debug("Retrieving product with queried name");
        return productRepository.findByProductName(productName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByType(String productType) {
        LOGGER.debug("Retrieving the list of all products matching productType");
        return productRepository.findByProductType(productType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductByBrand(String productBrand) {
        LOGGER.debug("Retrieving the list of all products matching productBrand");
        return productRepository.findByProductBrand(productBrand);
    }

    @Override
    public Product getProductById(UUID id) {
        LOGGER.debug("Retrieving the product matching id");
        return productRepository.findOne(id);
    }

}

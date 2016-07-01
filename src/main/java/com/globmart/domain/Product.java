package com.globmart.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
/**
 * Created by subramanian_p on 30-06-2016.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @NotNull
    @Size(min = 1, max = 50)
    private String productName;
    @NotNull
    @Size(min = 1, max = 50)
    private String productType;
    @NotNull
    @Size(min = 1, max = 50)
    private String productBrand;

    public Product() {
    }

    public Product(String productName, String productType, String productBrand) {
        this.productName = productName;
        this.productType = productType;
        this.productBrand = productBrand;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!productName.equals(product.productName)) return false;
        if (!productType.equals(product.productType)) return false;
        return productBrand.equals(product.productBrand);

    }

    @Override
    public int hashCode() {
        int result = productName.hashCode();
        result = 31 * result + productType.hashCode();
        result = 31 * result + productBrand.hashCode();
        return result;
    }
}

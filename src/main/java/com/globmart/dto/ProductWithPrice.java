package com.globmart.dto;

import com.globmart.domain.Product;

/**
 * Created by subramanian_p on 01-07-2016.
 */
public class ProductWithPrice extends Product {
    private GlobMartPrice globMartPrice;

    public ProductWithPrice(String productName, String productType, String productBrand, GlobMartPrice globMartPrice) {
        super(productName, productType, productBrand);
        this.globMartPrice = globMartPrice;
    }

    public ProductWithPrice(Product product, GlobMartPrice globMartPrice) {
        super(product.getProductName(),product.getProductType(),product.getProductBrand());
        this.globMartPrice = globMartPrice;
    }

    public GlobMartPrice getGlobMartPrice() {
        return globMartPrice;
    }

    public void setGlobMartPrice(GlobMartPrice globMartPrice) {
        this.globMartPrice = globMartPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ProductWithPrice that = (ProductWithPrice) o;

        return globMartPrice != null ? globMartPrice.equals(that.globMartPrice) : that.globMartPrice == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (globMartPrice != null ? globMartPrice.hashCode() : 0);
        return result;
    }
}

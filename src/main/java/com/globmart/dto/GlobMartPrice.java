package com.globmart.dto;

/**
 * Created by subramanian_p on 01-07-2016.
 */
public class GlobMartPrice {
    private String productName;
    private String productPrice;

    public GlobMartPrice(String productPrice, String productName) {
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public GlobMartPrice() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobMartPrice that = (GlobMartPrice) o;

        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        return productPrice != null ? productPrice.equals(that.productPrice) : that.productPrice == null;

    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        return result;
    }
}

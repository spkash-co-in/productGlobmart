package com.globmart.controller;

import com.globmart.dto.GlobMartPrice;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subramanian_p on 01-07-2016.
 */

@Component
public class PricesBean {
    @Autowired
    private PricesClient pricesClient;

    public PricesBean() {
    }

    @HystrixCommand(fallbackMethod = "defaultPrices")
    public List<GlobMartPrice> getPrice(String productName, String token) {
        return pricesClient.getPrice(productName, token);
    }

    public List<GlobMartPrice> defaultPrices(String productName,String token) {
        GlobMartPrice price = new GlobMartPrice();
        if (productName.equalsIgnoreCase("APPLE")) {
            price.setProductPrice("$500");
        } else {
            price.setProductPrice("$250");
        }
        price.setProductName(productName);
        ArrayList<GlobMartPrice> listPrice = new ArrayList<GlobMartPrice>();
        listPrice.add(price);
        return listPrice;
    }
}

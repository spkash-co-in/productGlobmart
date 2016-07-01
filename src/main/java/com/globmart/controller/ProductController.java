package com.globmart.controller;

import com.globmart.domain.Product;
import com.globmart.dto.GlobMartPrice;
import com.globmart.dto.ProductWithPrice;
import com.globmart.service.ProductService;
import com.globmart.service.exception.ProductAlreadyExistsException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by subramanian_p on 30-06-2016.
 */
@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;
    @Autowired
    PricesClient pricesClient;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PricesBean pricesBean;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Product createProduct(@RequestBody @Valid final Product product) {
        LOGGER.debug("Received request to create the {}", product);
        return productService.save(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") UUID id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> getProductByName(@RequestParam(value="name", required=false) String name,
                                    @RequestParam(value="type", required=false) String type,
                                    @RequestParam(value="brand", required=false) String brand) {
        LOGGER.debug("Received request to list product by name/type/brand");
        if (name!=null && type == null && brand == null) return productService.getProductByName(name);
        if (name==null && type != null && brand == null) return productService.getProductByType(type);
        if (name==null && type == null && brand != null) return productService.getProductByBrand(brand);
        return productService.getProductList();
    }

    @RequestMapping("/prices")
    public List<ProductWithPrice> getProductWithPrice(@RequestHeader("authorization") String authToken
    ){
        getHeadersInfo();
        GlobMartPrice price;
        List<Product> productList = productService.getProductList();
        List<ProductWithPrice> productWithPrices = new ArrayList<ProductWithPrice>();
        for (Product p : productList) {
            price = new GlobMartPrice();
                List<GlobMartPrice> pricesClientPrice = pricesBean.getPrice(p.getProductName(), authToken);
                if (pricesClientPrice.size()>0) {
                    price = pricesClientPrice.get(0);
                }
            productWithPrices.add(new ProductWithPrice(p, price));
        }
        return productWithPrices;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        return e.getMessage();
    }

    //get request headers
    private Map<String, String> getHeadersInfo() {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        System.out.println(map.toString());
        return map;
    }


}

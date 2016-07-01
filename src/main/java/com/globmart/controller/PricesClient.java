package com.globmart.controller;

import com.globmart.dto.GlobMartPrice;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by subramanian_p on 01-07-2016.
 */
@FeignClient("pricingCatalog")
public interface PricesClient {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<GlobMartPrice> getPrice(@RequestParam(value = "name") String name, @RequestHeader("authorization") String token);
}

package com.globmart.controller

import com.globmart.domain.Product
import com.globmart.service.ProductService
import spock.lang.Specification

import java.util.logging.Logger

/**
 * Created by subramanian_p on 02-07-2016.
 */
class ProductControllerSpec extends Specification{
    ProductController productController;
    ProductService productService = Mock();
    def setup() {
        productController = new ProductController(productService: productService)
    }

    def "search for all products"(){
        given:
        UUID uuid = UUID.randomUUID()
        String productName = "iPhone6"
        String productType = "Mobile"
        String productBrand = "Apple"
        def product = new Product(productName: productName,productType: productType, productBrand: productBrand, id: uuid)
        def productList = []
        productList << product

        and:
        1 * productService.getProductList() >> productList

        when:
        List<Product> response = productController.getProductByName(null,null,null)

        then:
        assert response.size() == 1
        assert response.get(0).productName == productName
        assert response.get(0).productType == productType
        assert response.get(0).productBrand == productBrand
        assert response.get(0).id == uuid
        println response.get(0).dump()
    }
}

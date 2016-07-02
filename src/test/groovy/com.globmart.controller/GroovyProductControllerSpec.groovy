package com.globmart.controller

import com.globmart.ProductCatalogApplication
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Shared
import spock.lang.Specification
import groovyx.net.http.RESTClient

import java.util.logging.Logger

/**
 * Created by subramanian_p on 02-07-2016.
 */
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = ProductCatalogApplication)
@WebAppConfiguration
@IntegrationTest("eureka.client.enabled:false")
class GroovyProductControllerSpec extends Specification{
    @Autowired
    ApplicationContext context

    def "test context loads"() {
        expect:
        context != null
        context.containsBean("productCatalogApplication")
    }

    @Shared
    def client = new RESTClient("http://admin:admin@localhost:8181")

    def "Get all products"() {
        when: "REST GET call performed"
        def listResponse = client.get(path : "")

        then: "it should have 10 products"
        with(listResponse) {
            status == 200
        }

        and: "product list should be 10"
        listResponse.data.size() == 10
        println "--->" + listResponse.toString()
        listResponse.getData().each {println it}

    }
}

package com.globmart;

import com.globmart.domain.Product;
import com.globmart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by subramanian_p on 30-06-2016.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ProductCatalogApplication implements CommandLineRunner{
    @Autowired
    ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product("Galaxy","Mobile","Samsung"));
        productRepository.save(new Product("Nexus","Mobile","Google"));
        productRepository.save(new Product("MotoG","Mobile","Motorola"));
        productRepository.save(new Product("iPhone6","Mobile","Apple"));
        productRepository.save(new Product("MacBook Air","Laptop","Apple"));
        productRepository.save(new Product("iPad","Tablet","Apple"));
        productRepository.save(new Product("Yoga","Tablet","Lenovo"));
        productRepository.save(new Product("ThinkPad","Tablet","Lenovo"));
        productRepository.save(new Product("K3 Note","Mobile","Lenovo"));
        productRepository.save(new Product("Surface","Tablet","Microsoft"));
    }
}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth .inMemoryAuthentication() .withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}


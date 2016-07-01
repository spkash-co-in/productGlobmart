package com.globmart.service;

import com.globmart.domain.Product;
import com.globmart.repository.ProductRepository;
import com.globmart.service.exception.ProductAlreadyExistsException;
import com.globmart.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        //productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void shouldSaveNewUser_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedUserShouldBeReturned() throws Exception {
//        final Product savedProduct = stubRepositoryToReturnUserOnSave();
//        final Product product = UserUtil.createUser();
//        final Product returnedProduct = productService.save(product);
//        // verify repository was called with product
//        verify(productRepository, times(1)).save(product);
//        assertEquals("Returned product should come from the repository", savedProduct, returnedProduct);
    }
/*
    private Product stubRepositoryToReturnUserOnSave() {
        Product product = UserUtil.createUser();
        when(productRepository.save(any(Product.class))).thenReturn(product);
        return product;
    }

    @Test
    public void shouldSaveNewUser_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingUser();
        try {
            productService.save(UserUtil.createUser());
            fail("Expected exception");
        } catch (ProductAlreadyExistsException ignored) {
        }
        verify(productRepository, never()).save(any(Product.class));
    }

    private void stubRepositoryToReturnExistingUser() {
        final Product product = UserUtil.createUser();
        when(productRepository.findOne(product.getId())).thenReturn(product);
    }

    @Test
    public void shouldListAllUsers_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingUsers(10);
        Collection<Product> list = productService.getProductList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(productRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingUsers(int howMany) {
        when(productRepository.findAll()).thenReturn(UserUtil.createUserList(howMany));
    }

    @Test
    public void shouldListAllUsers_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingUsers(0);
        Collection<Product> list = productService.getProductList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(productRepository, times(1)).findAll();
    }*/

}

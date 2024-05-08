package com.june.apiserver.repository;

import com.june.apiserver.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {
        Product product = Product.builder()
                .pname("test상품")
                .pdesc("test description")
                .price(10000)
                .build();

        product.addImageString(UUID.randomUUID() + "_" + "IMAGE1.jpg");
        product.addImageString(UUID.randomUUID() + "_" + "IMAGE2.jpg");

        productRepository.save(product);
    }
}
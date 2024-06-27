package com.june.apiserver.repository;

import com.june.apiserver.domain.Product;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.ProductDto;
import com.june.apiserver.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testInsert() {
        for (int i = 0; i < 20; i++) {
            Product product = Product.builder()
                    .pname("test상품_" + i)
                    .pdesc("test description")
                    .price(10000)
                    .build();

            product.addImageString(UUID.randomUUID() + "_" + "IMAGE1.jpg");
            product.addImageString(UUID.randomUUID() + "_" + "IMAGE2.jpg");

            productRepository.save(product);
        }
    }

    @Transactional
    @Test
    public void testDlete() {
        Long pno = 2L;
        productRepository.updateToDelete(2L, true);
    }

    @Test
    public void testUpdate() {
        Product product = productRepository.selectOne(1L).get();
        product.changePrice(3000);
        product.clearList();
        product.addImageString(UUID.randomUUID() + "_" + "PIMAGE1.jpg");
        product.addImageString(UUID.randomUUID() + "_" + "PIMAGE2.jpg");
        product.addImageString(UUID.randomUUID() + "_" + "PIMAGE3.jpg");

        productRepository.save(product);
    }

    @Test
    public void testList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
        Page<Object[]> result = productRepository.selectList(pageable);

        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testSearch() {
        PageRequestDto pageRequestDto = PageRequestDto.builder().build();
        productRepository.searchList(pageRequestDto);
    }

    @Test
    public void testRegister() {
        ProductDto productDto = ProductDto.builder()
                .pname("새로운 상품")
                .pdesc("신규 추가 상품입니다.")
                .price(1000).build();

        productDto.setUploadFileNames(
                List.of(
                        UUID.randomUUID() + "_" + "TEst1.jpg",
                        UUID.randomUUID() + "_" + "TEst2.jpg"
                ));

        productService.register(productDto);
    }
}
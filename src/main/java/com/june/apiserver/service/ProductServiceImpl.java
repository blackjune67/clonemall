package com.june.apiserver.service;

import com.june.apiserver.domain.Product;
import com.june.apiserver.domain.ProductImage;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.ProductDto;
import com.june.apiserver.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PageResponseDto<ProductDto> getList(PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() - 1,
                pageRequestDto.getSize(),
                Sort.by("pno").descending());
        Page<Object[]> result = productRepository.selectList(pageable);

        List<ProductDto> dtoList = result.get().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            ProductDto productDto = ProductDto.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .build();

            String imageStr = productImage.getFileName();
            productDto.setUploadFileNames(List.of(imageStr));

            return productDto;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDto.<ProductDto>withAll()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDto(pageRequestDto)
                .build();
    }

    @Override
    public Long register(ProductDto productDto) {
        Product product = dtoToEntity(productDto);
        return productRepository.save(product).getPno();
    }

    @Override
    public ProductDto get(Long pno) {
//        entityToDto()
        Optional<Product> result = productRepository.findById(pno);
        Product product = result.orElseThrow();
        return entityToDto(product);
    }

    @Override
    public void modify(ProductDto productDto) {
        Optional<Product> result = productRepository.findById(productDto.getPno());

        Product product = result.orElseThrow();

        product.changePrice(productDto.getPrice());
        product.changeName(productDto.getPname());
        product.changeDesc(productDto.getPdesc());
        product.changeDelFlag(productDto.isDelFlag());

        List<String> uploadFileNames = productDto.getUploadFileNames();
        product.clearList();

        if (uploadFileNames != null && !uploadFileNames.isEmpty()) {
            uploadFileNames.forEach(product::addImageString);
        }
        productRepository.save(product);
    }

    @Override
    public void remove(Long pno) {
//        productRepository.deleteById(pno);
        productRepository.updateToDelete(pno, true);
    }

    private ProductDto entityToDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .price(product.getPrice())
                .delFlag(product.isDelFlag())
                .build();

        List<ProductImage> imageList = product.getImageList();

        if (imageList == null || imageList.isEmpty()) {
            return productDto;
        }

        List<String> fileNameList = imageList.stream().map(productImage -> productImage.getFileName()).toList();
        productDto.setUploadFileNames(fileNameList);
        return productDto;
    }

    private Product dtoToEntity(ProductDto productDto) {
        Product product = Product.builder()
                .pname(productDto.getPname())
                .pdesc(productDto.getPdesc())
                .price(productDto.getPrice())
                .build();

        List<String> uploadFileNames = productDto.getUploadFileNames();

        if (uploadFileNames == null || uploadFileNames.isEmpty()) {
            return product;
        }

        uploadFileNames.forEach(product::addImageString);
        return product;
    }


}

package com.june.apiserver.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "tbl_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageList")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlag;

    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeName(String name) {
        this.pname = name;
    }

    public void changeDesc(String desc) {
        this.pdesc = desc;
    }


    public void addImage(ProductImage productImage) {
        productImage.setOrd(imageList.size());
        imageList.add(productImage);
    }

    // ????
    public void addImageString(String fileName) {
        ProductImage productImage = ProductImage.builder()
                .fileName(fileName)
                .build();
        addImage(productImage);
    }

    public void removeImage(int index) {
        imageList.remove(index);
    }

    public void clearList() {
        this.imageList.clear();
    }
}

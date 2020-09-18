package com.company.dev.productService.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = ProductDetails.PRODUCT_DETAILS)
@Getter
@Setter
public class ProductDetails implements Serializable {
    public static final String PRODUCT_DETAILS = "product_details";
    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer productId;

    @Column(name = "productImages")
    public String productImages;

    @Column(name = "productCategory_Id")
    public Integer categoryId;

    @Column(name = "color")
    public String color;

    @Column(name = "price")
    public Integer price;

}

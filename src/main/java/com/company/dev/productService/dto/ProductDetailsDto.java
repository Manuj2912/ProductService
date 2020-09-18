package com.company.dev.productService.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class ProductDetailsDto implements Serializable {
    public Integer productId;
    public MultipartFile[] productImageArray;
    public Integer categoryId;
    public String color;
    public Integer price;
}

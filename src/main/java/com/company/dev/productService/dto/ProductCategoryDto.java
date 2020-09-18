package com.company.dev.productService.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
@Setter
@Getter
public class ProductCategoryDto {
    public Integer id;
    public String category;
}

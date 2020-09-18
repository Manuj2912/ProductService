package com.company.dev.productService.repository;

import com.company.dev.productService.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryInterface extends  PagingAndSortingRepository<ProductCategory,Integer> {
}

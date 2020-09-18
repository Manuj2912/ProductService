package com.company.dev.productService.repository;

import com.company.dev.productService.dao.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsInterface extends JpaRepository<ProductDetails,Integer> {
}

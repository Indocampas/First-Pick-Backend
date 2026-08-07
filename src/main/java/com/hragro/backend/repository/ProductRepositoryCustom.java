package com.hragro.backend.repository;

import com.hragro.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> searchProducts(String search, String category, Pageable pageable);
}
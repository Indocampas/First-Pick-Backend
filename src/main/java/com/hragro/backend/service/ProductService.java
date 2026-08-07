package com.hragro.backend.service;

import com.hragro.backend.dto.PageRequestDto;
import com.hragro.backend.dto.PageResponseDto;
import com.hragro.backend.model.Product;
import com.hragro.backend.repository.ProductRepository;
import com.hragro.backend.repository.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepositoryCustom productRepositoryCustom;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public PageResponseDto<Product> getProductsWithPagination(PageRequestDto request) {
        Sort.Direction direction = request.getSortDirection().equalsIgnoreCase("asc") 
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(
                request.getPage(), 
                request.getSize(), 
                Sort.by(direction, request.getSortBy())
        );

        Page<Product> page = productRepositoryCustom.searchProducts(
                request.getSearch(), 
                request.getCategory(), 
                pageable
        );

        return new PageResponseDto<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.ProductRequest;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductRequest request) {
        Product p = new Product();
        updateFields(p, request);
        return productRepository.save(p);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product update(Long id, ProductRequest request) {
        Product product = getById(id);
        updateFields(product, request);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    private void updateFields(Product p, ProductRequest request) {
        p.setName(request.name());
        p.setDescription(request.description());
        p.setPrice(request.price());
        p.setStockQuantity(request.stockQuantity());
        p.setImageUrl(request.imageUrl());
    }
}

package org.example.th3.service;

import lombok.RequiredArgsConstructor;
import org.example.th3.dto.ProductRequest;
import org.example.th3.entity.Product;
import org.example.th3.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(ProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .sku(request.getSku())
                .build();

        return productRepository.save(product);
    }
}
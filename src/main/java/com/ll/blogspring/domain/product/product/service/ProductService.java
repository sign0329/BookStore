package com.ll.blogspring.domain.product.product.service;

import com.ll.blogspring.domain.Book.Book.entity.Book;
import com.ll.blogspring.domain.product.product.entity.Product;
import com.ll.blogspring.domain.product.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(Book book) {
        if (book.getProduct() != null) return book.getProduct();

        Product product = Product.builder()
                .relTypeCode(book.getModeName())
                .relId(book.getId())
                .name(book.getTitle())
                .price(book.getPrice())
                .build();

        productRepository.save(product);
        book.setProduct(product);

        return product;
    }
}

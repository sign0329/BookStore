package com.ll.blogspring.domain.Book.repository;

import com.ll.blogspring.domain.Book.Book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

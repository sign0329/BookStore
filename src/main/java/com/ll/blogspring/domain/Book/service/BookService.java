package com.ll.blogspring.domain.Book.service;


import com.ll.blogspring.domain.Book.Book.entity.Book;
import com.ll.blogspring.domain.Book.repository.BookRepository;
import com.ll.blogspring.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book createBook(Member author, String title, String body, int price){
        Book book = Book.builder()
                .author(author)
                .title(title)
                .body(body)
                .price(price)
                .build();

        bookRepository.save(book);

        return book;
    }

}

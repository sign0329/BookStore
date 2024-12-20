package com.ll.blogspring.global.initData;

import com.ll.blogspring.domain.Book.Book.entity.Book;
import com.ll.blogspring.domain.Book.service.BookService;
import com.ll.blogspring.domain.cash.cash.entity.CashLog;
import com.ll.blogspring.domain.product.cart.service.CartService;
import com.ll.blogspring.domain.member.service.MemberService;
import com.ll.blogspring.domain.product.order.entity.PurchaseOrder;
import com.ll.blogspring.domain.product.order.service.OrderService;
import com.ll.blogspring.domain.product.product.entity.Product;
import com.ll.blogspring.domain.product.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import com.ll.blogspring.domain.member.member.entity.Member;


@Configuration
@RequiredArgsConstructor
public class NotProd {

    @Autowired
    @Lazy
    private NotProd self;
    private final MemberService memberService;
    private final BookService bookService;
    private final ProductService productService;
    private final CartService cartItemService;
    private final OrderService orderService;
    private final CartService cartService;

    @Bean
    ApplicationRunner initNotProd(){
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if (memberService.findByUsername("admin").isPresent()) return;
        Member memberAdmin = memberService.join("admin", "1234").getData();
        Member memberUser1 = memberService.join("user1", "1234").getData();
        Member memberUser2 = memberService.join("user2", "1234").getData();
        Member memberUser3 = memberService.join("user3", "1234").getData();

        Book book1 = bookService.createBook(memberUser1, "책 제목 1", "책 내용 1", 10_000);
        Book book2 = bookService.createBook(memberUser2, "책 제목 2", "책 내용 2", 20_000);
        Book book3 = bookService.createBook(memberUser2, "책 제목 3", "책 내용 3", 30_000);
        Book book4 = bookService.createBook(memberUser3, "책 제목 4", "책 내용 4", 40_000);
        Book book5 = bookService.createBook(memberUser3, "책 제목 5", "책 내용 5", 50_000);
        Book book6 = bookService.createBook(memberUser3, "책 제목 6", "책 내용 6", 60_000);

        Product product1 = productService.createProduct(book3);
        Product product2 = productService.createProduct(book4);
        Product product3 = productService.createProduct(book5);
        Product product4 = productService.createProduct(book6);

        cartService.addItem(memberUser2, product1);
        cartService.addItem(memberUser2, product2);
        cartService.addItem(memberUser2, product3);
        cartService.addItem(memberUser3, product2);
        cartService.addItem(memberUser3, product3);
        cartService.addItem(memberUser3, product3);


        cartItemService.addItem(memberUser1, product1);
        cartItemService.addItem(memberUser2, product2);
        cartItemService.addItem(memberUser3, product3);

        System.out.println("memberUser1.restCash : " + memberUser1.getRestCash());

        memberService.addCash(memberUser1, 150_000, CashLog.EvenType.충전__무통장입금, memberUser1);

        System.out.println("memberUser1.restCahsh : " + memberUser1.getRestCash());

        orderService.createFromCart(memberUser1);

        PurchaseOrder purchaseOrder1 = orderService.createFromCart(memberUser1);

        long orderPayPrice = purchaseOrder1.calcPayPrice();

        orderService.payByCashOnly(purchaseOrder1);

        memberService.addCash(memberUser3, 150_000, CashLog.EvenType.충전__무통장입금, memberUser3);
        PurchaseOrder purchaseOrder2 = orderService.createFromCart(memberUser3);
        orderService.payByCashOnly(purchaseOrder2);
        orderService.refund(purchaseOrder2);
    }
}
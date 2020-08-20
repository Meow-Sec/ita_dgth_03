package com.ita.demo.controller;

import com.ita.demo.model.BookingRequest;
import com.ita.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("{assetId}")
    public ResponseEntity<String> getOrder(@PathVariable String assetId) {
        return bookService.getOrders(assetId);
    }

    @GetMapping("{assetId}/version/{version}")
    public ResponseEntity<String> getOrderByVersion(@PathVariable String assetId, @PathVariable String version) {
        return bookService.getOrders(assetId, version);
    }

    @PutMapping("/documents/bookingRequest/{assetId}/version/{version}")
    public ResponseEntity<String> updateOrder(@PathVariable String assertId, @PathVariable String version , @RequestBody BookingRequest book) {
        return bookService.updateBook(assertId, version, book);
    }

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody String bookingRequest){
        ResponseEntity<String> exchange = bookService.saveOrder(bookingRequest);
        return exchange;
    }

}

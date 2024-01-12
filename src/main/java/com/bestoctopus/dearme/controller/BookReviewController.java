package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.dto.BookReviewDto;
import com.bestoctopus.dearme.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookReviewController {

    private final BookReviewService bookReviewListService;

    @PostMapping
    public ResponseEntity<?> saveBookData(){
        return ResponseEntity.ok().body();
    }

//    @GetMapping
//    public List<BookReviewDto> getBookReviewList() {
//        return bookReviewListService.getAllBookReviewList().stream()
//                .map(BookReviewDto::new)
//                .collect(Collectors.toList());
//
//
//    }

}

package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.dto.BookReviewListDto;
import com.bestoctopus.dearme.service.BookReviewListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookReviewListController {

    private final BookReviewListService bookReviewListService;


    @GetMapping("")
    public List<BookReviewListDto> getBookReviewList() {
        return bookReviewListService.getAllBookReviewList().stream()
                .map(BookReviewListDto::new)
                .collect(Collectors.toList());


    }

}

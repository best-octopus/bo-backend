package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.Tag;
import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.dto.BookReviewRequestDto;
import com.bestoctopus.dearme.dto.BookReviewResponseDto;
import com.bestoctopus.dearme.service.BookReviewService;
import com.bestoctopus.dearme.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookReviewController {

    private final BookReviewService bookReviewService;
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> saveBookReview(@RequestBody BookReviewRequestDto bookReviewDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        BookData bookData = bookReviewDto.getBookData();
        bookReviewService.saveBookData(bookData);

        BookReview bookReview = bookReviewService.saveBookReview(bookReviewDto, userId);
        tagService.updateTag(bookReview, bookReviewDto.getTags());
//        BookReviewResponseDto response = BookReviewResponseDto.fromEntity(bookReview);

        return ResponseEntity.ok().body("리뷰가 저장되었습니다.");
    }

    @GetMapping
    public ResponseEntity<BookReviewResponseDto> getBookReview(@RequestParam("id") Long id){
        BookReview bookReview = bookReviewService.getBookReview(id);
        BookReviewResponseDto response = BookReviewResponseDto.fromEntity(bookReview);
        return ResponseEntity.ok().body(response);
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

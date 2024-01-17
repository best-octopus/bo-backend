package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.dto.BookReviewListDto;
import com.bestoctopus.dearme.dto.BookReviewRequestDto;
import com.bestoctopus.dearme.dto.BookReviewResponseDto;
import com.bestoctopus.dearme.service.BookReviewService;
import com.bestoctopus.dearme.service.component.BookDataService;
import com.bestoctopus.dearme.service.component.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookReviewController {

    private final BookReviewService bookReviewService;
    private final BookDataService bookDataService;

    @PostMapping
    public ResponseEntity<?> saveBookReview(@RequestBody BookReviewRequestDto bookReviewDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        BookData bookData = bookReviewDto.getBookData();
        bookDataService.saveBookData(bookData);
        bookReviewService.saveBookReview(bookReviewDto, userId);

        return ResponseEntity.ok().body("리뷰가 저장되었습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReviewResponseDto> getBookReview(@PathVariable("id") Long id) {
        BookReview bookReview = bookReviewService.getBookReview(id);
        BookReviewResponseDto response = BookReviewResponseDto.fromEntity(bookReview);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Slice<BookReviewListDto>> getBookReviewList(@RequestParam("page") int page) {
        Slice<BookReviewListDto> response = bookReviewService.getBookReviewList(page);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/tag")
    public ResponseEntity<Slice<BookReviewListDto>> getBookReviewListForTag(@RequestParam("tags") String tags, @RequestParam("page") int page) {
        long[] tagArr = Arrays.stream(tags.split(",")).mapToLong(Long::parseLong).toArray();
        Slice<BookReviewListDto> response = bookReviewService.getBookReviewListForTag(tagArr, page);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/like")
    public ResponseEntity<Slice<BookReviewListDto>> getBookReviewListForLikes(@RequestParam("page") int page) {
        Slice<BookReviewListDto> response = bookReviewService.getBookReviewListForLikes(page);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/best")
    public ResponseEntity<Slice<BookReviewListDto>> getBookReviewListForBest() {
        Slice<BookReviewListDto> response = bookReviewService.getBookReviewListForLikes(0, 5);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{bookReviewId}/like")
    public ResponseEntity<?> like(@PathVariable long bookReviewId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        bookReviewService.like(userId, bookReviewId);
        return ResponseEntity.ok().body("좋아요");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        bookReviewService.delete(id);
        return ResponseEntity.ok().body("삭제 완료");
    }

//    @GetMapping()

//    @GetMapping
//    public List<BookReviewDto> getBookReviewList() {
//        return bookReviewListService.getAllBookReviewList().stream()
//                .map(BookReviewDto::new)
//                .collect(Collectors.toList());
//
//
//    }

}

package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.repository.BookReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;

    public List<BookReview> getAllBookReviewList() {
        return bookReviewRepository.findAll();
    }

}

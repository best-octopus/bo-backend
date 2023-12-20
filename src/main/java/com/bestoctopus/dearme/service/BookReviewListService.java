package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.repository.BookReviewListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class BookReviewListService {

    private final BookReviewListRepository bookReviewListRepository;

    public List<BookReview> getAllBookReviewList() {
        return bookReviewListRepository.findAll();
    }



}

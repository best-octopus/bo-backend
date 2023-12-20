package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReviewListRepository extends JpaRepository<BookReview, Long> {

//    Optional<BookReview> findById(Long bookReviewId);


}

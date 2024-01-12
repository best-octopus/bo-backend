package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewRepository extends JpaRepository<BookReview, Long> {

//    Optional<BookReview> findById(Long bookReviewId);


}

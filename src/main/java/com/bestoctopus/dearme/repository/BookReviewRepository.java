package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long>{
    Optional<BookReview> findById(long id);
}
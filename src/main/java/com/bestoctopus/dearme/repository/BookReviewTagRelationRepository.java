package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookReviewTagRelationRepository extends JpaRepository<BookReviewTagRelation, Long> {
    Optional<Set<BookReviewTagRelation>> findAllByBookReview(BookReview bookReview);
}

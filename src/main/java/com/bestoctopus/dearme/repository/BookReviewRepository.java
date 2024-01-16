package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookReview;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long>{
//    @Query("select distinct b from BookReview b left join fetch b.tags left join fetch b.likes where b.id=:id")
//    Optional<BookReview> findById(@Param("id")long id);
    Optional<BookReview> findById(long id);

    Slice<BookReview> findSliceBy(Pageable pageable);
}
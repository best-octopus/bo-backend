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
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
    Optional<BookReview> findById(long id);

    Slice<BookReview> findSliceBy(Pageable pageable);


    @Query("select distinct b from BookReview b join fetch b.bookData join fetch b.user " +
            "left join fetch b.tags r " +
            "left join fetch r.tag " +
            "join b.tags t " +
            "left join fetch b.likes " +
            "left join fetch b.comments " +
            "where t.tag.id in :tags")
    Slice<BookReview> findSliceByTag(@Param("tags") long[] tags, Pageable pageable);

    @Query("select distinct b from BookReview b join fetch b.bookData join fetch b.user " +
            "left join fetch b.tags r " +
            "left join fetch r.tag " +
            "left join fetch b.likes " +
            "left join fetch b.comments " +
            "order by size(b.likes) DESC, b.id DESC")
    Slice<BookReview> findSliceByLikes(Pageable pageable);

    @Query("select distinct b from BookReview b join fetch b.bookData join fetch b.user " +
            "left join fetch b.tags r " +
            "left join fetch r.tag " +
            "join b.tags t " +
            "left join fetch b.likes " +
            "left join fetch b.comments " +
            "where t.tag.id in :tags "+
            "order by size(b.likes) DESC, b.id DESC")
    Slice<BookReview> findSliceByTagLike(@Param("tags") long[] tags, Pageable pageable);
}
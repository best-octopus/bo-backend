package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.domain.relation.LikeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRelationRepository extends JpaRepository<LikeRelation,Long> {

    Optional<LikeRelation> findByBookReviewAndUser(BookReview bookReview, User user);
}

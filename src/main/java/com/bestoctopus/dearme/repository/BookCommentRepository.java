package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
}

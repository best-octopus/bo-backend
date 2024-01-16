package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
}

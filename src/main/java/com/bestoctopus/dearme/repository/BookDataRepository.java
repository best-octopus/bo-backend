package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDataRepository extends JpaRepository<BookData,Long> {
    Optional<BookData> findByIsbn(long isbn);
}

package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.Memo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    Slice<Memo> findBydateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}

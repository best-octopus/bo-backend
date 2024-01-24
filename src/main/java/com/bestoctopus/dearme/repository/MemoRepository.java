package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.Memo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

    Slice<Memo> findBydateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    @Query("SELECT DISTINCT m FROM Memo m " +
            "JOIN MemoTagRelation mtr ON mtr.memo = m " +
            "JOIN Tag t ON t = mtr.tag " +
            "WHERE t.id IN :tags")
    Slice<Memo> findMemosIn(@Param("tags") List<Integer> tags, Pageable pageable);
}

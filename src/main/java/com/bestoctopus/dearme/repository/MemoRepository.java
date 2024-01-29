package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.Memo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

    @Query("SELECT m FROM Memo m LEFT JOIN m.likes l GROUP BY m.id ORDER BY COUNT(l) DESC")
    Page<Memo> findMemosWithLikes(Pageable pageable);

    @Query("select distinct m from Memo m " +
            "join fetch m.user " +
            "left join fetch m.tags r " +
            "join m.tags t " +
            "where t.id in :tags")
    Slice<Memo> findSliceByTag(@Param("tags") int[] tags, Pageable pageable);
}
package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BucketList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BucketListRepository extends JpaRepository<BucketList, Long>{
    List<BucketList> findBydateBetween(LocalDate startDate, LocalDate endDate);

    Optional<BucketList> findById(Long bucketId);

    void deleteById(Long bucketId);
}

package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.dto.BucketListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BucketListRepository extends JpaRepository<BucketList, Long>{
    List<BucketList> findBydateBetween(LocalDate startDate, LocalDate endDate);

//    Optional<BucketList> findById(Long id);

    void deleteById(Long bucketId);
}

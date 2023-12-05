package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.dto.PutBucketListDto;
import com.bestoctopus.dearme.repository.BucketListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BucketListService {

    private final BucketListRepository bucketListRepository;

    public List<BucketList> getAllBucketList(LocalDate startDate, LocalDate endDate) {
        return bucketListRepository.findBydateBetween(startDate, endDate);
    }

    public BucketList postBucketList(BucketList bucketList) {
        return bucketListRepository.save(bucketList);
    }

    public boolean putBucketList(Long bucketId, PutBucketListDto putBucketListDto) {
        Optional<BucketList> optionalBucketList = bucketListRepository.findById(bucketId);

        if (optionalBucketList.isPresent()) { //bucketId가 존재할 경우에만 update 실행
            BucketList bucketList = optionalBucketList.get();
            bucketList.update(putBucketListDto);
            bucketListRepository.save(bucketList);
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteBucketList(Long bucketId) {
        Optional<BucketList> optionalBucketList = bucketListRepository.findById(bucketId);

        if (optionalBucketList.isPresent()) { //bucketId가 존재할 경우에만 delete 실행
            bucketListRepository.deleteById(bucketId);
            return true;
        } else {
            return false;
        }
    }
}
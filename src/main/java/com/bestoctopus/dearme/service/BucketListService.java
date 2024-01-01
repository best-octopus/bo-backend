package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.BucketListDto;
import com.bestoctopus.dearme.dto.PutBucketListDto;
import com.bestoctopus.dearme.repository.BucketListRepository;
import com.bestoctopus.dearme.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class BucketListService {

    private final BucketListRepository bucketListRepository;

    private final UserRepository userRepository;

    public List<BucketList> getAllBucketList(LocalDate startDate, LocalDate endDate) {
        return bucketListRepository.findBydateBetween(startDate, endDate);
    }


    public BucketList postBucketList(BucketListDto bucketListDto, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        BucketList bucketList = bucketListDto.toEntity(user);

        return bucketListRepository.save(bucketList);
    }

    public boolean putBucketList(Long bucket_id, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        Optional<BucketList> optionalBucketList = bucketListRepository.findById(bucket_id);

        if (optionalBucketList.isPresent()) { //bucketId가 존재할 경우에만 update 실행
            BucketList bucketList = optionalBucketList.get();
            if (bucketList.getUser().equals(user)) {
                Boolean status = bucketList.getStatus();
                bucketList.update(!status);
                bucketListRepository.save(bucketList);
                return true;
            }
            else { return false; }
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
package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.dto.BucketListDto;
import com.bestoctopus.dearme.repository.UserRepository;
import com.bestoctopus.dearme.service.BucketListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketListController {

    private final BucketListService bucketListService;

    private final UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getBucketList(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) YearMonth date) {

        List<BucketList> bucketLists = bucketListService.getAllBucketList(date);

        List<BucketListDto> collect = bucketLists.stream()
                .map(m-> new BucketListDto(m.getStatus(), m.getGoal(), m.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }


    @PostMapping("")
    public ResponseEntity<?> PostBucketList(@RequestBody @Valid BucketListDto bucketListDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        bucketListService.postBucketList(bucketListDto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{bucket_id}")
    public ResponseEntity<?> putBucketList(@PathVariable("bucket_id") Long bucketId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        boolean updated = bucketListService.putBucketList(bucketId, userId);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{bucket_id}")
    public ResponseEntity<?> deleteBucketList(@PathVariable("bucket_id") Long bucketId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        boolean deleted = bucketListService.deleteBucketList(bucketId, userId);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

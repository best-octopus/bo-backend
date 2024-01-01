package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.BucketListDto;
import com.bestoctopus.dearme.dto.PutBucketListDto;
import com.bestoctopus.dearme.dto.ResultDto;
import com.bestoctopus.dearme.repository.UserRepository;
import com.bestoctopus.dearme.service.BucketListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketListController {

    private final BucketListService bucketListService;

    private final UserRepository userRepository;

    @GetMapping("")
    public List<BucketListDto> getBucketList(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<BucketList> bucketLists = bucketListService.getAllBucketList(startDate, endDate);

        List<BucketListDto> collect = bucketLists.stream()
                .map(m-> new BucketListDto(m.getStatus(), m.getGoal(), m.getDate()))
                .collect(Collectors.toList());

        return collect;
    }


    @PostMapping("")
    public ResponseEntity<?> PostBucketList( // @RequestHeader("Authorization") String accessToken,
                                             @RequestBody @Valid BucketListDto bucketListDto) {

        String user_id = "1";

        bucketListService.postBucketList(bucketListDto, user_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{bucket_id}")
    public ResponseEntity<?> putBucketList(//@RequestHeader("Authorization") String accessToken,
                                          @PathVariable("bucket_id") Long bucketId) {

        String user_id = "1";

        boolean updated = bucketListService.putBucketList(bucketId, user_id);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{bucket_id}")
    public ResponseEntity<?> deleteBucketList(//@RequestHeader("Authorization") String accessToken,
                                      @PathVariable("bucket_id") Long bucketId) {

        boolean deleted = bucketListService.deleteBucketList(bucketId);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

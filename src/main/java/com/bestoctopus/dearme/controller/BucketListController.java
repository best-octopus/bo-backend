package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.dto.BucketListDto;
import com.bestoctopus.dearme.dto.PutBucketListDto;
import com.bestoctopus.dearme.dto.ResultDto;
import com.bestoctopus.dearme.service.BucketListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/bucket")
@RequiredArgsConstructor
public class BucketListController {

    private final BucketListService bucketListService;


    @GetMapping("")
    public ResponseEntity<?> getBucketList(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(bucketListService.getAllBucketList(startDate, endDate));

    }

    @PostMapping("")
    public ResponseEntity<?> PostBucketList( // @RequestHeader("Authorization") String accessToken,
                                             @RequestBody @Valid BucketListDto bucketListDto) {

        bucketListService.postBucketList(bucketListDto.toEntity());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{itemId}")
    public ResponseEntity<?> putBucketList(//@RequestHeader("Authorization") String accessToken,
                                            @PathVariable("bucket_id") Long bucketId,
                                            @RequestBody @Valid PutBucketListDto putBucketListDto) {

        boolean updated = bucketListService.putBucketList(bucketId, putBucketListDto);

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

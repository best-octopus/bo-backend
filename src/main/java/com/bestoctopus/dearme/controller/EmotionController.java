package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.domain.DailyEmo;
import com.bestoctopus.dearme.domain.Emotion;
import com.bestoctopus.dearme.dto.DailyEmoDto;
import com.bestoctopus.dearme.service.DailyEmoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emotion")
@RequiredArgsConstructor
public class EmotionController {

    private final DailyEmoService dailyEmoService;

    @GetMapping("")
    public ResponseEntity<?> getEmotionList(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(dailyEmoService.getAllEmotionList(startDate, endDate));
    }

    @GetMapping("/count")
    public List<Map.Entry<Emotion, Integer>> getEmotionCount(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<DailyEmo> dailyEmo = dailyEmoService.getAllEmotionList(startDate, endDate);

        return dailyEmoService.getEmotionCount(dailyEmo);
    }


    @PostMapping("")
    public ResponseEntity<?> postEmotion( // @RequestHeader("Authorization") String accessToken,
                                             @RequestBody @Valid DailyEmoDto dailyEmoDto) {

        String user_id = "1";

        dailyEmoService.postDailyEmo(dailyEmoDto, user_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{emotion_id}")
    public ResponseEntity<?> putEmotion ( // @RequestHeader("Authorization") String accessToken,
                                          @PathVariable("emotion_id") Long emotionId,
                                          @RequestParam("emotion") Emotion emotion) {

        boolean updated = dailyEmoService.putDailyEmo(emotionId, emotion);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{emotion_id}")
    public ResponseEntity<?> deleteBucketList(//@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("emotion_id") Long emotionId) {

        boolean deleted = dailyEmoService.deleteDailyEmo(emotionId);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emotion")
@RequiredArgsConstructor
public class EmotionController {

    private final DailyEmoService dailyEmoService;

    @GetMapping("")
    public ResponseEntity<?> getEmotionList(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

//        DailyEmoDto dailyEmoDto = dailyEmoService.getEmotion(date);

        return ResponseEntity.ok(dailyEmoService.getEmotion(date));
    }

    @GetMapping("/count")
    public ResponseEntity<List<Map.Entry<Emotion, Integer>>> getEmotionCount(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<DailyEmo> dailyEmo = dailyEmoService.getAllEmotionList(startDate, endDate);

        return ResponseEntity.ok(dailyEmoService.getEmotionCount(dailyEmo));
    }


    @PostMapping("")
    public ResponseEntity<?> postEmotion( // @RequestHeader("Authorization") String String,
                                             @RequestBody @Valid DailyEmoDto dailyEmoDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        boolean posted = dailyEmoService.postDailyEmo(dailyEmoDto, userId);

        if (posted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    @DeleteMapping("/{emotion_id}")
    public ResponseEntity<?> deleteBucketList(//@RequestHeader("Authorization") String accessToken,
                                              @PathVariable("emotion_id") Long emotionId) {
        String user_id = "1";
        boolean deleted = dailyEmoService.deleteDailyEmo(emotionId, user_id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */
}

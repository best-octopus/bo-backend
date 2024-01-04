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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emotion")
@RequiredArgsConstructor
public class EmotionController {

    private final DailyEmoService dailyEmoService;

    @GetMapping("")
    public List<DailyEmoDto> getEmotionList(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<DailyEmo> dailyEmoList = dailyEmoService.getAllEmotionList(startDate, endDate);

        List<DailyEmoDto> dailyEmoDto = dailyEmoList.stream()
                .map(m-> new DailyEmoDto(m.getEmotion(), m.getDate()))
                .collect(Collectors.toList());

        return dailyEmoDto;
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

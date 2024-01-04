package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.domain.DailyEmo;
import com.bestoctopus.dearme.domain.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class DailyEmoDto {
    private Emotion emotion;

    private LocalDate date;

    public DailyEmo toEntity(){
        return DailyEmo.builder()
                .emotion(this.emotion)
                .date(this.date)
                .build();
    }
}

package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BucketList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BucketListDto {

    private Boolean status;

    private String goal;

    private LocalDate date;

    public BucketList toEntity(){
        return BucketList.builder()
                .status(this.status)
                .goal(this.goal)
                .date(this.date)
                .build();

    }
}

package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BucketList;
import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BucketListDto {

    private Boolean status;

    private String goal;

    private LocalDate date;

    public BucketList toEntity(User user_id){
        return BucketList.builder()
                .status(this.status)
                .goal(this.goal)
                .date(this.date)
                .user_id(user_id)
                .build();

    }
}

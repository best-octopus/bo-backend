package com.bestoctopus.dearme.dto;


import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.Status;
import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MemoDto {
    private Status status;

    private LocalDate date;

    private String content;

    public Memo toEntity(User user){
        return Memo.builder()
                .status(this.status)
                .date(this.date)
                .content(this.content)
                .user(user)
                .build();

    }
}

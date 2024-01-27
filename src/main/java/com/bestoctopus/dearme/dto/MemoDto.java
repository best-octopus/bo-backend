package com.bestoctopus.dearme.dto;


import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.MemoType;
import com.bestoctopus.dearme.domain.Status;
import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemoDto {

    private MemoType memoType;

    private Status status;

    private LocalDate date;

    private String content;

    private List<String> answers;

    private Set<String> tags;

    public Memo toEntity(User user){
        return Memo.builder()
                .memoType(this.memoType)
                .status(this.status)
                .date(this.date)
                .content(this.content)
                .answers(this.answers)
                .user(user)
                .build();
    }

    public static MemoDto fromEntity(Memo memo){
        return MemoDto.builder()
                .memoType(memo.getType())
                .status(memo.getStatus())
                .date(memo.getDate())
                .content(memo.getContent())
                .answers(memo.getAnswers())
                .build();
    }
}
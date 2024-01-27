package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.MemoType;
import com.bestoctopus.dearme.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GetMemoDto {

    private Long id;

    private MemoType memoType;

    private Status status;

    private LocalDate date;

    private int likes;

    private Set<String> tags;

    public static GetMemoDto fromEntity(Memo memo) {
        return GetMemoDto.builder()
                .id(memo.getId())
                .memoType(memo.getType())
                .status(memo.getStatus())
                .date(memo.getDate())
                .likes(memo.getLikes().size())
                .tags(memo.getTagNameSet())
                .build();
    }
}
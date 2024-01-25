package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.MemoType;
import com.bestoctopus.dearme.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GetMemoDto {

    private Long id;

    private MemoType memoType;

    private Status status;

    private LocalDate date;
}
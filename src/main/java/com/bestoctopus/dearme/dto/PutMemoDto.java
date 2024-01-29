package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutMemoDto {
    private String content;

    private Status status;
}
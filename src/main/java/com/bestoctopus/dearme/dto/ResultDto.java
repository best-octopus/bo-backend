package com.bestoctopus.dearme.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultDto {

    private boolean result;

    public ResultDto(Boolean result) {
        this.result = result;
    }
}

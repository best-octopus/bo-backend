package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BucketList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class PutBucketListDto {

    private Boolean status;

    private String goal;
}

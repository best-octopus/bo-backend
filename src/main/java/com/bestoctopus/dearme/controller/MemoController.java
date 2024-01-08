package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.dto.MemoDto;
import com.bestoctopus.dearme.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("")
    public ResponseEntity<?> getMemoList(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Memo> memoList = memoService.getAllMemoList(startDate, endDate);

        List<MemoDto> memoDto = memoList.stream()
                .map(m-> new MemoDto(m.getStatus(), m.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(memoDto);
    }
}

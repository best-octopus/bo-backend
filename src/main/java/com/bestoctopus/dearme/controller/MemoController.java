package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.dto.GetMemoDto;
import com.bestoctopus.dearme.dto.MemoDto;
import com.bestoctopus.dearme.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

        List<GetMemoDto> getMemoDto = memoList.stream()
                .map(m-> new GetMemoDto(m.getStatus(), m.getDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(getMemoDto);
    }


    @PostMapping("")
    public ResponseEntity<?> postMemo(@RequestBody @Valid MemoDto memoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        memoService.postMemo(memoDto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

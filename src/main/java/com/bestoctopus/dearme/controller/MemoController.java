package com.bestoctopus.dearme.controller;


import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.dto.GetMemoDto;
import com.bestoctopus.dearme.dto.MemoDto;
import com.bestoctopus.dearme.dto.PutMemoDto;
import com.bestoctopus.dearme.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                         @RequestParam(required = false, defaultValue = "0", value = "page") int page) {

        Pageable pageable = PageRequest.of(page, 10);

        Slice<Memo> memoList = memoService.getAllMemoList(startDate, endDate, pageable);

        List<GetMemoDto> getMemoDto = memoList.stream()
                .map(m-> new GetMemoDto(m.getStatus(), m.getDate()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(getMemoDto, HttpStatus.OK);
    }

    @GetMapping("/{memo_id}")
    public ResponseEntity<?> getMemo(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                     @RequestParam(required = false, defaultValue = "0", value = "page") int page) {

        Pageable pageable = PageRequest.of(page, 10);

        Slice<Memo> memoList = memoService.getAllMemoList(startDate, endDate, pageable);

        List<MemoDto> memoDto = memoList.stream()
                .map(m-> new MemoDto(m.getStatus(), m.getDate(), m.getContent()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(memoDto);
    }

    @PostMapping("")
    public ResponseEntity<?> postMemo(@RequestBody @Valid MemoDto memoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        memoService.postMemo(memoDto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{memo_id}")
    public ResponseEntity<?> putMemo(@PathVariable("memo_id") Long memoId,
                                     @RequestBody @Valid PutMemoDto putMemoDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = (String)authentication.getPrincipal();

        boolean updated = memoService.putMemo(memoId, userId, putMemoDto);

        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{memo_id}")
    public ResponseEntity<?> deleteMemo(@PathVariable("memo_id") Long memoId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String)authentication.getPrincipal();

        boolean deleted = memoService.deleteMemo(memoId, userId);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 좋아요 개발
}

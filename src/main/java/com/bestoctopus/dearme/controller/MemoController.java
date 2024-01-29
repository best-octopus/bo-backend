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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;


    @GetMapping("")
    public ResponseEntity<?> getMemoList(@RequestParam(required = false, defaultValue = "0", value = "page") int page) {

        Slice<GetMemoDto> memoList = memoService.getAllMemoList(page);

        return new ResponseEntity<>(memoList, HttpStatus.OK);
    }


    @GetMapping("/{memo_id}")
    public ResponseEntity<?> getMemo(@PathVariable("memo_id") Long memo_id) {

        Optional<Memo> memo = memoService.getMemo(memo_id);
        Memo memo2 = memo.orElseThrow();
        MemoDto memoDto = MemoDto.fromEntity(memo2);

        return ResponseEntity.ok(memoDto);
    }

    @GetMapping("/tag")
    public ResponseEntity<?> getMemoTagList(@RequestParam("tags") String tags,
                                            @RequestParam(required = false, defaultValue = "0", value = "page") Integer page) {

        int[] tagList = Arrays.stream(tags.split(",")).mapToInt(Integer::parseInt).toArray();

        Slice<GetMemoDto> memoList = memoService.getMemoTagList(tagList, page);

        return new ResponseEntity<>(memoList, HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<?> getBestMemo() {

        Stream<GetMemoDto> memoList = memoService.getBestMemo();

        return new ResponseEntity<>(memoList, HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<?> getLikesMemo(int page) {

        Slice<GetMemoDto> memoList = memoService.getMemoSortedByLikes(page);

        return new ResponseEntity<>(memoList, HttpStatus.OK);
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
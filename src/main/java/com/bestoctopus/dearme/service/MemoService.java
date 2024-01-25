package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.GetMemoDto;
import com.bestoctopus.dearme.dto.MemoDto;
import com.bestoctopus.dearme.dto.PutMemoDto;
import com.bestoctopus.dearme.repository.MemoRepository;
import com.bestoctopus.dearme.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    private final UserRepository userRepository;

    public Slice<GetMemoDto> getAllMemoList(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());

        Slice<Memo> memoList = memoRepository.findAll(pageable);

        return memoList.map(GetMemoDto::fromEntity);
    }

    public Optional<Memo> getMemo(Long memo_id) {

        return memoRepository.findById(memo_id);
    }

//    public Slice<Memo> getMemoTagList(List<Integer>tags, Integer page) {
//        Pageable pageable = PageRequest.of(page, 10);
//
//        return memoRepository.findMemosIn(tags, pageable);
//    }

    public Stream<GetMemoDto> getBestMemo() {

        Pageable pageable = PageRequest.of(0, 5);
        Page<Memo> memoLikeCountsPage = memoRepository.findMemosWithLikes(pageable);

        List<Memo> bestMemo = memoLikeCountsPage.getContent();
        System.out.println(bestMemo);

        return bestMemo.stream().map(GetMemoDto::fromEntity);
    }

    public Memo postMemo(MemoDto memoDto, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        System.out.println(memoDto);
        Memo memo = memoDto.toEntity(user);
        return memoRepository.save(memo);
    }


    public boolean putMemo(Long memo_id, String user_id, PutMemoDto putMemoDto) {
        User user = userRepository.findById(user_id).orElseThrow();

        Optional<Memo> optionalMemo = memoRepository.findById(memo_id);

        if (optionalMemo.isPresent()) {
            Memo memo = optionalMemo.get();
            if (memo.getUser().equals(user)) {
                memo.update(putMemoDto);
                memoRepository.save(memo);
                return true;
            }
            else { return false; }
        } else {
            return false;
        }
    }


    public boolean deleteMemo(Long memo_id, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        Optional<Memo> optionalMemo = memoRepository.findById(memo_id);

        if (optionalMemo.isPresent()) {
            Memo memo = optionalMemo.get();

            if (memo.getUser().equals(user)) {
                memoRepository.deleteById(memo_id);
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }
}
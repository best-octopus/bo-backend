package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.MemoDto;
import com.bestoctopus.dearme.dto.PutMemoDto;
import com.bestoctopus.dearme.repository.MemoRepository;
import com.bestoctopus.dearme.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    private final UserRepository userRepository;

    public Slice<Memo> getAllMemoList(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return memoRepository.findBydateBetween(startDate, endDate, pageable);
    }

    public Memo postMemo(MemoDto memoDto, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

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
package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.MemoDto;
import com.bestoctopus.dearme.repository.MemoRepository;
import com.bestoctopus.dearme.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    private final UserRepository userRepository;

    public List<Memo> getAllMemoList(LocalDate startDate, LocalDate endDate) {
        return memoRepository.findBydateBetween(startDate, endDate);
    }

    public Memo postMemo(MemoDto memoDto, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        Memo memo = memoDto.toEntity(user);
        return memoRepository.save(memo);
    }
}

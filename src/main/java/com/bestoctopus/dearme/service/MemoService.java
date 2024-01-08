package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.repository.MemoRepository;
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

    public List<Memo> getAllMemoList(LocalDate startDate, LocalDate endDate) {
        return memoRepository.findBydateBetween(startDate, endDate);
    }

}

package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.DailyEmo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyEmoRepository extends JpaRepository<DailyEmo, Long> {
    List<DailyEmo> findBydateBetween(LocalDate startDate, LocalDate endDate);
    DailyEmo findByDate(LocalDate date);
}

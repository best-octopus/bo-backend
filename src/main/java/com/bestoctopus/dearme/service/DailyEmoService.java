package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.DailyEmo;
import com.bestoctopus.dearme.domain.Emotion;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.DailyEmoDto;
import com.bestoctopus.dearme.repository.DailyEmoRepository;
import com.bestoctopus.dearme.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyEmoService {
    private final DailyEmoRepository dailyEmoRepository;

    private final UserRepository userRepository;

    public List<DailyEmo> getAllEmotionList(LocalDate startDate, LocalDate endDate) {
        return dailyEmoRepository.findBydateBetween(startDate, endDate);
    }

    public List<Map.Entry<Emotion, Integer>> getEmotionCount(List<DailyEmo> dailyEmo) {

        int happy=0, soso=0, sad=0, angry=0;

        for (DailyEmo Emo : dailyEmo) {
            if (Emo.getEmotion() == Emotion.HAPPY)  { happy++; }
            else if (Emo.getEmotion() == Emotion.SOSO)  { soso++;}
            else if (Emo.getEmotion() == Emotion.SAD)  { sad++; }
            else    { angry++; }
        }

        Map<Emotion, Integer> emotionCountMap = new HashMap<>();
        emotionCountMap.put(Emotion.HAPPY, happy);
        emotionCountMap.put(Emotion.SOSO, soso);
        emotionCountMap.put(Emotion.SAD, sad);
        emotionCountMap.put(Emotion.ANGRY, angry);

        return new ArrayList<>(emotionCountMap.entrySet());
    }

    public DailyEmo postDailyEmo(DailyEmoDto dailyEmoDto, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        DailyEmo dailyEmo = dailyEmoDto.toEntity(user);
        return dailyEmoRepository.save(dailyEmo);
    }

    /*
    public boolean deleteDailyEmo(Long emotionId, String user_id) {
        User user = userRepository.findById(user_id).orElseThrow();

        Optional<DailyEmo> optionalDailyEmo = dailyEmoRepository.findById(emotionId);

        if (optionalDailyEmo.isPresent()) {
            DailyEmo dailyEmo = optionalDailyEmo.get();
            if (dailyEmo.getUser().equals(user)) {
                dailyEmoRepository.deleteById(emotionId);
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }
     */

}

package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.dto.PutBucketListDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "daily")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class DailyEmo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Emotion emotion;

    @Builder
    public DailyEmo(LocalDate date, Emotion emotion, User user) {
        this.date = date;
        this.emotion = emotion;
        this.user = user;
    }

    public void update(Emotion emotion) {
        if (emotion != null) this.emotion = emotion;
    }
}

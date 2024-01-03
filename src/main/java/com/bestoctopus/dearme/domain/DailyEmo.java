package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.dto.PutBucketListDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "daily")
@Getter
public class DailyEmo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
}

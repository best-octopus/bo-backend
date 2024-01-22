package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.dto.PutBucketListDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bucket_list")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class BucketList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Boolean status;

    @Column(length = 30, nullable = false)
    private String goal;

    @Column(nullable = false)
    private LocalDate date;


    @Builder
    public BucketList(boolean status, String goal, LocalDate date, User user_id) {
        this.status = status;
        this.goal = goal;
        this.date = date;
        this.user = user_id;
    }

    public void update(Boolean status) {
        this.status = status;
    }
}

package com.bestoctopus.dearme.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "bucket_list")
@Getter
public class BucketList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Status status;

    @Column(length = 30, nullable = false)
    private String goal;

    @Column(nullable = false)
    private LocalDate date;
}

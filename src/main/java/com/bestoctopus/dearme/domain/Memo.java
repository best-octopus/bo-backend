package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.MemoTagRelation;
import com.bestoctopus.dearme.dto.PutMemoDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "memo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //종류 넣기

    @Column(nullable = false)
    private String content;

    //질문 넣기

    //좋아요

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "memo")
    private Set<MemoTagRelation> tags;

    @Builder
    public Memo(Status status, LocalDate date, String content, User user) {
        this.status = status;
        this.date = date;
        this.content = content;
        this.user = user;
    }


    public void update(PutMemoDto putMemoDto) {
        this.content = putMemoDto.getContent();
        this.status = putMemoDto.getStatus();
    }
}

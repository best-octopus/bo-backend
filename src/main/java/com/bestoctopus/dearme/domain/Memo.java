package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.MemoTagRelation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "memo")
@Getter
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //종류 넣기

    @Column(nullable = false)
    private String content;

    //질문 넣기

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "memo")
    private List<MemoTagRelation> tags;
}

package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.MemoTagRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "memo")
    private List<MemoTagRelation> tags;

    @Builder
    public Memo(Status status, LocalDate date, String content, User user) {
        this.status = status;
        this.date = date;
        this.content = content;
        this.user = user;
    }

}

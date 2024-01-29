package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.MemoLikeRelation;
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
import java.util.stream.Collectors;

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

    @Column(nullable = false)
    private MemoType type;

    @Column(nullable = false)
    private String content;

    @ElementCollection
    @CollectionTable(name = "answers", joinColumns = @JoinColumn(name = "memo_id"))
    @Column(nullable = false)
    private List<String> answers;

    //좋아요
    @OneToMany(mappedBy = "memo")
    private Set<MemoLikeRelation> likes;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "memo")
    private Set<MemoTagRelation> tags;

    @Builder
    public Memo(MemoType memoType, Status status, LocalDate date, String content, List<String> answers, User user) {
        this.type = memoType;
        this.status = status;
        this.date = date;
        this.content = content;
        this.answers = answers;
        this.user = user;
    }


    public void update(PutMemoDto putMemoDto) {
        this.content = putMemoDto.getContent();
        this.status = putMemoDto.getStatus();
    }

    public Set<String> getTagNameSet(){
        return getTags().stream().map(tagRel->tagRel.getTag().getName()).collect(Collectors.toSet());
    }
}
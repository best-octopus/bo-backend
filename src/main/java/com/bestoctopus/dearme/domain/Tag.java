package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.domain.relation.MemoTagRelation;
import com.bestoctopus.dearme.domain.relation.UserTagRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //태그 주제 넣기

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private final Set<UserTagRelation> userTags = new HashSet<>();

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private final Set<MemoTagRelation> memoTags = new HashSet<>();

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private final Set<BookReviewTagRelation> bookReviewTags = new HashSet<>();
}

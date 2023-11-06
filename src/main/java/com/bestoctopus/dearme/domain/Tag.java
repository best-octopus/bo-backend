package com.bestoctopus.dearme.domain;

import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.domain.relation.MemoTagRelation;
import com.bestoctopus.dearme.domain.relation.UserTagRelation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //태그 주제 넣기

    @OneToMany(mappedBy = "tag")
    private Set<UserTagRelation> userTags;

    @OneToMany(mappedBy = "tag")
    private Set<MemoTagRelation> memoTags;

    @OneToMany(mappedBy = "tag")
    private Set<BookReviewTagRelation> bookReviewTags;
}

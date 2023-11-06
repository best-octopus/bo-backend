package com.bestoctopus.dearme.domain;


import com.bestoctopus.dearme.domain.relation.LikeRelation;
import com.bestoctopus.dearme.domain.relation.UserTagRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(length = 15, nullable = false)
    private String id;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 15, nullable = false, unique = true)
    private String nickname;

    @Column(length = 15, nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<BucketList> bucketLists;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<BookReview> bookReviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Memo> memos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<DailyEmo> dailyEmos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserTagRelation> tags;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<LikeRelation> likes;

    @Builder
    public User(String id, String password, String nickname, String name) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
    }
}

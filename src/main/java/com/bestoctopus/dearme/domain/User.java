package com.bestoctopus.dearme.domain;


import com.bestoctopus.dearme.domain.relation.LikeRelation;
import com.bestoctopus.dearme.domain.relation.UserTagRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(length = 15, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(length = 15, nullable = false, unique = true)
    private String nickname;

    @Column(length = 15, nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final Set<BucketList> bucketLists = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final Set<BookReview> bookReviews = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final Set<Memo> memos = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final Set<DailyEmo> dailyEmos = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final Set<UserTagRelation> tags = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final Set<LikeRelation> likes = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private final List<BookComment> comments = new ArrayList<>();

    @Builder
    public User(String id, String password, String nickname, String name) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
    }
}

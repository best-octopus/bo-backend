package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memo_like_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoLikeRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memo_id",nullable = false)
    private Memo memo;
}

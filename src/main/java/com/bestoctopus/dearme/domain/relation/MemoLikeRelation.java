package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memo_like_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoLikeRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "memo_id", nullable = false)
    private Memo memo;

    @Builder
    public MemoLikeRelation(User user, Memo memo) {
        this.user = user;
        this.memo = memo;
    }
}

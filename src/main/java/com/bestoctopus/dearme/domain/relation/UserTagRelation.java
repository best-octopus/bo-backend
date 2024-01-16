package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.Tag;
import com.bestoctopus.dearme.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tag_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="tag_id",nullable = false)
    private Tag tag;
}

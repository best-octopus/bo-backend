package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.Tag;
import com.bestoctopus.dearme.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "user_tag_realtion")
@Getter
@Builder
public class UserTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id",nullable = false)
    private Tag tag;
}

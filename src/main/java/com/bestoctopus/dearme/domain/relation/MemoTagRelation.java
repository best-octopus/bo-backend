package com.bestoctopus.dearme.domain.relation;

import com.bestoctopus.dearme.domain.Memo;
import com.bestoctopus.dearme.domain.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "memo_tag_relation")
@Getter
@Builder
public class MemoTagRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id", nullable = false)
    private Memo memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

}

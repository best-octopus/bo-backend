package com.bestoctopus.dearme.repository;

import com.bestoctopus.dearme.domain.relation.LikeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRelationRepository extends JpaRepository<LikeRelation,Long> {
}

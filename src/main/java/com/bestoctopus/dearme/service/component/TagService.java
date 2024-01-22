package com.bestoctopus.dearme.service.component;

import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.Tag;
import com.bestoctopus.dearme.domain.relation.BookReviewTagRelation;
import com.bestoctopus.dearme.repository.BookReviewTagRelationRepository;
import com.bestoctopus.dearme.repository.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final BookReviewTagRelationRepository bookReviewTagRelationRepository;

    public Tag addTag(String name){
        Tag tag = Tag.builder().name(name).build();
        return tagRepository.save(tag);
    }

    @Transactional
    public void updateTagForBookReview(BookReview bookReview, Set<Long> tagIds) {
        Optional<Set<BookReviewTagRelation>> rels = bookReviewTagRelationRepository.findAllByBookReview(bookReview);
        rels.ifPresent(bookReviewTagRelationRepository::deleteAll);

        Set<Tag> tags = tagIds.stream().map(id -> tagRepository.findById(id).orElseThrow(()-> new NoSuchElementException("tag가 존재하지 않습니다.")))
                .collect(Collectors.toSet());

        tags.forEach(tag ->
                bookReviewTagRelationRepository.save(
                        BookReviewTagRelation.builder()
                                .bookReview(bookReview)
                                .tag(tag)
                                .build()
                )
        );
    }
}

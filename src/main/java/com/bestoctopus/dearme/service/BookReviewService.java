package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.BookComment;
import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.domain.relation.LikeRelation;
import com.bestoctopus.dearme.dto.BookCommentDto;
import com.bestoctopus.dearme.dto.BookReviewListDto;
import com.bestoctopus.dearme.dto.BookReviewRequestDto;
import com.bestoctopus.dearme.exception.NotFoundUserException;
import com.bestoctopus.dearme.exception.NotValidateException;
import com.bestoctopus.dearme.repository.*;
import com.bestoctopus.dearme.service.component.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookReviewService {
    private final LikeRelationRepository likeRelationRepository;
    private final UserRepository userRepository;
    private final BookReviewRepository bookReviewRepository;
    private final BookCommentRepository bookCommentRepository;

    private final TagService tagService;

    private final int PAGE_SIZE = 5;

    @Transactional
    public void saveBookReview(BookReviewRequestDto bookReviewDto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        BookReview bookReview = bookReviewRepository.save(bookReviewDto.toEntity(user));
        tagService.updateTagForBookReview(bookReview, bookReviewDto.getTags());
    }

    public BookReview getBookReview(long id) {
        return bookReviewRepository.findById(id).orElseThrow();
    }

    public Slice<BookReviewListDto> getBookReviewList(int page) {
        Slice<BookReview> bookReviews = bookReviewRepository.findSliceBy(PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id")));
        return bookReviews.map(BookReviewListDto::fromEntity);
    }

    public Slice<BookReviewListDto> getBookReviewListForTag(long[] tags, int page) {
        Slice<BookReview> bookReviews = bookReviewRepository.findSliceByTag(tags, PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id")));
        return bookReviews.map(BookReviewListDto::fromEntity);
    }

    public Slice<BookReviewListDto> getBookReviewListForLikes(int page) {
        Slice<BookReview> bookReviews = bookReviewRepository.findSliceByLikes(PageRequest.of(page, PAGE_SIZE));
        return bookReviews.map(BookReviewListDto::fromEntity);
    }

    public Slice<BookReviewListDto> getBookReviewListForLikes(int page, int page_size) {
        Slice<BookReview> bookReviews = bookReviewRepository.findSliceByLikes(PageRequest.of(page, page_size));
        return bookReviews.map(BookReviewListDto::fromEntity);
    }

    public void like(String userId, long bookReviewId) {
        BookReview bookReview = bookReviewRepository.findById(bookReviewId).orElseThrow(() -> new NotValidateException("해당하는 BookReview가 없습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotValidateException("해당하는 user가 없습니다."));
        Optional<LikeRelation> likeRelation = likeRelationRepository.findByBookReviewAndUser(bookReview, user);
        if (likeRelation.isPresent()) {
            likeRelationRepository.delete(likeRelation.get());
        } else {
            likeRelationRepository.save(LikeRelation.builder()
                    .user(user)
                    .bookReview(bookReview)
                    .build());
        }
    }

    public void delete(long id) {
        BookReview bookReview = bookReviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 id의 BookReveiw가 없습니다."));
        bookReviewRepository.delete(bookReview);
    }

    public BookCommentDto saveBookComment(String userId, long bookReviewId, BookCommentDto bookCommentDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException("작성자가 user가 아닙니다."));
        BookReview bookReview = bookReviewRepository.findById(bookReviewId).orElseThrow(() -> new NotValidateException("해당 게시글이 존재하지 않습니다."));

        BookComment bookComment = bookCommentRepository.save(bookCommentDto.toEntity(user,bookReview));
        return BookCommentDto.fromEntity(bookComment);
    }
}

//    public List<BookReview> getAllBookReviewList() {
//        return bookReviewListRepository.findAll();
//    }

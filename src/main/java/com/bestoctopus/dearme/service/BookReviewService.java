package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.BookReviewListDto;
import com.bestoctopus.dearme.dto.BookReviewRequestDto;
import com.bestoctopus.dearme.exception.NotFoundUserException;
import com.bestoctopus.dearme.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookReviewService {
    private final LikeRelationRepository likeRelationRepository;
    private final UserRepository userRepository;
    private final BookDataRepository bookDataRepository;
    private final BookReviewRepository bookReviewRepository;

    private final int PAGE_SIZE = 5;

    public BookData saveBookData(BookData request_bookData) {
        long isbn = request_bookData.getIsbn();
        Optional<BookData> bookData = bookDataRepository.findById(isbn);
        return bookData.orElseGet(() -> bookDataRepository.save(request_bookData));
    }

    public BookReview saveBookReview(BookReviewRequestDto bookReviewDto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
//        Set<User> likeUsers = bookReviewDto.getLikes().stream()
//                .map(nickname->userRepository.findByNickname(nickname).orElseThrow())
//                .collect(Collectors.toSet());
        return bookReviewRepository.save(bookReviewDto.toEntity(user));
    }

    public BookReview getBookReview(long id){
        return bookReviewRepository.findById(id).orElseThrow();
    }

    public Slice<BookReviewListDto> getBookReviewList(int page){
        Slice<BookReview> bookReviews = bookReviewRepository.findAll(PageRequest.of(page,PAGE_SIZE, Sort.by(Sort.Direction.DESC,"id")));
        return bookReviews.map(BookReviewListDto::fromEntity);
    }
}

//    public List<BookReview> getAllBookReviewList() {
//        return bookReviewListRepository.findAll();
//    }

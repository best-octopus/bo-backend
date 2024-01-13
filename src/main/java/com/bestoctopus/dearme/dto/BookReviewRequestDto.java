package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.domain.BookReview;
import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BookReviewRequestDto {
    private String title;

    private String content;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private final LocalDate lastEditTime;

    private BookData bookData;

    private Set<Long> tags;

//    private Set<String> likes;

    public BookReview toEntity(User user){
        return BookReview.builder()
                .user(user)
                .title(title)
                .content(content)
                .bookData(bookData)
                .build();
    }
}

package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.BookData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BookDataDto {

    private long isbn;

    private String title;

    private String imgUrl;

    private String author;

    private String publisher;

    private String description;

    public static BookDataDto fromEntity(BookData bookData) {
        return BookDataDto.builder()
                .isbn(bookData.getIsbn())
                .title(bookData.getTitle())
                .imgUrl(bookData.getImgUrl())
                .author(bookData.getAuthor())
                .publisher(bookData.getPublisher())
                .description(bookData.getDescription())
                .build();
    }
}

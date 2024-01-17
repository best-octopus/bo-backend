package com.bestoctopus.dearme.service.component;

import com.bestoctopus.dearme.domain.BookData;
import com.bestoctopus.dearme.repository.BookDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDataService {
    private final BookDataRepository bookDataRepository;

    public void saveBookData(BookData request_bookData) {
        long isbn = request_bookData.getIsbn();
        Optional<BookData> bookData = bookDataRepository.findById(isbn);
        bookData.orElseGet(() -> bookDataRepository.save(request_bookData));
    }
}

//package com.bestoctopus.dearme;
//
//import com.bestoctopus.dearme.domain.BookReview;
//import com.bestoctopus.dearme.domain.BucketList;
//import com.bestoctopus.dearme.dto.BucketListDto;
//import com.bestoctopus.dearme.repository.BookReviewRepository;
//import com.bestoctopus.dearme.repository.BucketListRepository;
//import com.bestoctopus.dearme.service.BookReviewService;
//import com.bestoctopus.dearme.service.BucketListService;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class DearmeApplicationTests {
//    @Mock
//    private BucketListRepository bucketListRepository;
//
//    @InjectMocks
//    private BucketListService bucketListService;
//
//    @Mock
//    private BookReviewRepository bookReviewRepository;
//
//    @InjectMocks
//    private BookReviewService bookReviewService;
//
//
////    @Test
////    public void postBucketList() {
////        BucketList bucketList = new BucketList(true, "yeah", LocalDate.parse("2002-01-03"));
////
////        Mockito.when(bucketListRepository.save(Mockito.any(BucketList.class))).thenReturn(bucketList);
////
////        BucketList result = bucketListService.postBucketList(bucketList);
////
////        System.out.println("bucketlist : " + bucketList);
////        System.out.println("post result : " + result);
////    }
//
//
////    @Test
////    public void getBucketList() {
////        LocalDate startDate = LocalDate.parse("2001-12-31");
////        LocalDate endDate = LocalDate.parse("2002-01-03");
////
////        LocalDate date1 = LocalDate.parse("1888-12-31");
////        LocalDate date2 = LocalDate.parse("2002-01-01");
////        LocalDate date3 = LocalDate.parse("2005-01-01");
////
////        List<BucketList> testList = Arrays.asList(
////                new BucketList(true, "Entry 1", LocalDate.parse("1888-12-31")),
////                new BucketList(true, "Entry 2", LocalDate.parse("2002-01-01")),
////                new BucketList(true, "Entry 2", LocalDate.parse("2005-01-01"))
////        );
////
////        Mockito.when(bucketListRepository.findBydateBetween(startDate, endDate)).thenReturn(testList);
////
////        List<BucketList> result = bucketListService.getAllBucketList(startDate, endDate);
////
////        System.out.println(ResponseEntity.ok(result));
////    }
//
//
//    @Test
//    public void getBookReview() {
//
//        List<BookReview> bookReview = Arrays.asList(
//                new BookReview(1L, "hey", "con", LocalDate.parse("2023-01-01")),
//                new BookReview(2L, "ho", "con2", LocalDate.parse("2002-01-02")),
//                new BookReview(3L, "hh", "con3", LocalDate.parse("2020-02-02"))
//        );
//
//        Mockito.when(bookReviewRepository.findAll()).thenReturn(bookReview);
//
//        List<BookReview> result = bookReviewService.getAllBookReviewList();
//
//        System.out.println(ResponseEntity.ok(result));
//    }
//}

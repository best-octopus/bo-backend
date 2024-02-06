INSERT INTO user(id,name,nickname,password)
    VALUES ('ajou','ajou','ajou','$2a$10$9AtkU7ggytyDifPOmwNHnOw8lDeQuMAu/nIMK9Pcm3yTHvCyA8TVm'),
    ('chamny','chamny','chamny','$2a$10$9AtkU7ggytyDifPOmwNHnOw8lDeQuMAu/nIMK9Pcm3yTHvCyA8TVm'),
    ('moon','moon','moon','$2a$10$44BLhzwkX2AF/JG/e46Soe7JsmUFkU5HnJemFFk9bxtkxxmu1PuP2');

INSERT INTO book_data(isbn,title,img_url,author,publisher,description)
    VALUES (1234,'샤브샤브 맛있게 먹는 법','www.url','정현지','정현지컴퍼니','이 책은 샤브샤브가 먹고싶었던 정현지의...'),
           (33,'도전 720점','www.url','아주대학교','대우법인','정현지 졸업시켜주세요'),
           (78,'아르바이트 하는 법','www.url','사장','메가커피','알바안하고 돈벌고 싶어요');

INSERT INTO book_review(created_date,modified_date,content,title,book_data_id,user_id) VALUES
    ('2024-01-11T16:37:45.495968','2024-01-16T16:37:45.495968','샤브샤브에 죽해서 먹고싶다.','샤브샤브',1234,'ajou'),
    ('2024-02-01T11:33:46.495002','2024-02-02T17:00:00.123122','영어공부 하고 싶지 않아','토익',33,'chamny'),
    ('2024-02-03T18:15:16.495968','2024-02-05T20:30:21.495968','알바하기 싫어요','대학생 아르바이트',78,'moon');

INSERT INTO tag(name) VALUES
    ('식욕'),('성욕'),('수면욕'),('인간관계'),('연애'),('진로'),('학업'),('취업');

INSERT INTO book_review_tag_relation(book_review_id,tag_id) VALUES
    (1,1),(1,2),(2,7),(3,4),(3,8);

INSERT INTO like_relation(book_review_id,user_id) VALUES
    (1,'chamny'),(2,'ajou'),(2,'moon'),(3,'ajou');
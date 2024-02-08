INSERT INTO user(id,name,nickname,password)
    VALUES ('ajou','ajou','ajou','$2a$10$9AtkU7ggytyDifPOmwNHnOw8lDeQuMAu/nIMK9Pcm3yTHvCyA8TVm'),
    ('chamny','chamny','chamny','$2a$10$9AtkU7ggytyDifPOmwNHnOw8lDeQuMAu/nIMK9Pcm3yTHvCyA8TVm'),
    ('moon','moon','moon','$2a$10$44BLhzwkX2AF/JG/e46Soe7JsmUFkU5HnJemFFk9bxtkxxmu1PuP2');

INSERT INTO book_data(isbn,title,img_url,author,publisher,description)
    VALUES (1234,'샤브샤브 맛있게 먹는 법','www.url','정현지','정현지컴퍼니','이 책은 샤브샤브가 먹고싶었던 정현지의...'),
           (33,'도전 720점','www.url','아주대학교','대우법인','정현지 졸업시켜주세요'),
           (78,'아르바이트 하는 법','www.url','사장','메가커피','알바안하고 돈벌고 싶어요'),
           (7,'광교 갤러리아 맛집 탐방','www.url','갤러리아','베이커리','딸기크림이 아주 맛있어요'),
           (11,'버터링 신메뉴','www.url','해태','해태 연구소','버터링 신메뉴 딥딸기 소개합니다'),
           (19433,'꿈의 과학','www.url','꿈과학자','드림컴퍼니','이 책은 꿈을 어떻게 꾸게 되는지 설명합니다. 꿈이란 말이죠 꿈이에요. 왜꾸냐고요? 그걸 알면 다들 꿈잘꿔서 꿈속에서 살지 왜 살겠어요? 생각을 하시란 말이에요.'),
           (99,'뇌과학의 근본','www.url','좌뇌우뇌?','뇌는내꺼','뇌가 어떻게 인간의 신경에 관여하는지 설명하는 책입니다. 뇌로인해 좌지우지 되는 우리의 호르몬과 이로 인한 우울증 등 다양한 정신병과의 인과관계도 설명할거에요. 라고했지만 안할겁니다. 알게뭐에요?'),
           (81,'다이어트 성공비법','www.url','김종국','헬스트레이너','사실 김종국은 헬스트레이너가 아니라 가수입니다. 몰랐죠?');

INSERT INTO book_review(created_date,modified_date,content,title,star,book_data_id,user_id) VALUES
    ('2024-01-11T16:37:45','2024-01-16T16:37:45','샤브샤브에 죽해서 먹고싶다.','샤브샤브',3,1234,'ajou'),
    ('2024-02-01T11:33:46','2024-02-02T17:00:00','영어공부 하고 싶지 않아','토익',1,33,'chamny'),
    ('2024-02-03T18:15:18','2024-02-05T20:30:21','알바하기 싫어요','대학생 아르바이트',2,78,'moon'),
    ('2024-02-04T18:15:16','2024-02-05T21:33:14','컵케이크 먹고싶어요','딸기 바닐라 컵케이크',5,7,'chamny'),
    ('2024-02-06T01:03:21','2024-02-06T16:45:41','버터링은 역시 버터맛이 최고','버터링 고찰',3,11,'ajou'),
    ('2024-02-06T12:25:47',null,'꿈을 너무 많이 꿔요ㅜㅜ\n꿈에서 정국이가 나오면 맨날 꾸고 싶을텐데...무서운 꿈만 꿔요ㅜㅜ','꿈 안꾸는 법이 뭐죠',2,19433,'moon'),
    ('2024-02-06T15:56:03',null,'뇌과학이 이렇게 흥미롭다는걸 처음 알았어요\n너무 재밌는데요?\n정말 추천합니다.','제목쓰기 귀찮아요',4,99,'ajou'),
    ('2024-02-07T05:18:09',null,'이 책을 읽고 다이어트를 시도해봤지만 실패했어요\n다이어트 너무 어려워요ㅜ','다이어트 실패 후기',2,81,'ajou');

INSERT INTO book_comment(book_review_id,created_date,modified_date,user_id,text) VALUES
    (1,'2024-01-11T16:37:45','2024-01-16T16:37:45','chamny','샤브샤브 저도 좋아요'),
    (2,'2024-02-01T11:33:46','2024-02-02T17:00:00','ajou','왜 한글이 만국 공통어가 아닌지...'),
    (3,'2024-02-03T18:15:18','2024-02-05T20:30:21','chamny','로또 당첨되고 싶어요!ㅜㅜㅜ'),
    (2,'2024-02-04T18:15:16','2024-02-05T21:33:14','moon','저는 토익 잘봤는데~'),
    (3,'2024-02-06T01:03:21','2024-02-06T16:45:41','ajou','과외 추천합니다.'),
    (6,'2024-02-07T18:15:18',null,'chamny','저도 꿈을 많이 꾸는데 제 꿈에도 정국이가 안나와요ㅜㅜㅜ'),
    (7,'2024-02-07T18:15:16',null,'moon','뇌를 해부해보고 싶어요'),
    (8,'2024-02-07T01:03:21',null,'ajou','다이어트? 그게뭐죠 전 다 먹을거에요');


INSERT INTO tag(name) VALUES
    ('식욕'),('가족'),('수면욕'),('인간관계'),('연애'),('진로'),('학업'),('취업');

INSERT INTO book_review_tag_relation(book_review_id,tag_id) VALUES
    (1,1),(1,2),(2,7),(3,4),(3,8),(4,1),(4,4),(4,7),(5,6),(5,7);

INSERT INTO like_relation(book_review_id,user_id) VALUES
    (1,'chamny'),(2,'ajou'),(2,'moon'),(3,'ajou'),(4,'moon'),(7,'chamny'),(7,'moon');
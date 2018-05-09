Drop table foodManaging;

CREATE TABLE foodManaging--음식관리테이블
  ( country    VARCHAR2 (20) ,
    foodSerial VARCHAR2 (10) NOT NULL ,
    foodType   VARCHAR2 (10) ,
    foodName   VARCHAR2 (30) NOT NULL ,
    foodPrice  NUMBER (5) ); 
ALTER TABLE foodManaging ADD CONSTRAINT foodManaging_PK PRIMARY KEY ( foodSerial, foodName ) ;

SELECT * FROM foodManaging;

insert into foodManaging
values('한식','K001','요리류','두부김치',10000);
insert into foodManaging
values('한식','K002','탕류','얼큰꽃게오뎅탕',12000);
insert into foodManaging
values('한식','K003','육류','묵은지보쌈',15000);
insert into foodManaging
values('한식','K004','전류','대왕오징어해물파전',10000);
insert into foodManaging
values('한식','K005','탕류','국물닭볶음탕',15000);
insert into foodManaging
values('한식','K006','탕류','신선로',25000);

insert into foodManaging
values('일식','J001','튀김류','왕새우튀김',12000);
insert into foodManaging
values('일식','J002','튀김류','치즈돈까스',12000);
insert into foodManaging
values('일식','J003','탕류','꼬치오뎅우동',10000);
insert into foodManaging
values('일식','J004','회류','참다랑어회',30000);
insert into foodManaging
values('일식','J005','구이류','메로구이',25000);
insert into foodManaging
values('일식','J006','전류','특제오꼬노미야끼',10000);

insert into foodManaging
values('양식','W001','튀김류','모듬감자튀김',12000);
insert into foodManaging
values('양식','W002','육류','수제소세지구이',13000);
insert into foodManaging
values('양식','W003','튀김류','황금치킨',12000);
insert into foodManaging
values('양식','W004','육류','돼지바베큐',15000);

insert into foodManaging
values('주류','t001','소주','참이슬',3000);
insert into foodManaging
values('주류','t002','소주','처음처럼',3000);
insert into foodManaging
values('주류','t003','소주','C1',3000);
insert into foodManaging
values('주류','t004','소주','좋은데이',3000);

insert into foodManaging
values('주류','t005','맥주','생맥주500CC',3000);
insert into foodManaging
values('주류','t006','맥주','생맥주1000cc',6000);
insert into foodManaging
values('주류','t007','맥주','흑맥주500cc',4500);

update foodManaging
set foodPrice = 4000
where foodSerial = 't007';

insert into foodManaging
values('주류','t008','탁주','생막걸리',4000);
insert into foodManaging
values('주류','t009','탁주','꿀동동주',4000);


select FOODNAME,FOODPRICE from foodManaging where FOODNAME = '황금치킨';

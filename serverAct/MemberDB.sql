create sequence memberNumber;
drop sequence memberNumber;

drop table memberInfo;

select * from memberInfo;

create table memberInfo(
  memberNumber number(10),
  codeNumber varchar2(21) primary key,
  age number(3),
  gender varchar2(8),
  name varchar2(25) not null,
  phone varchar2(35),
  birthday varchar2(10)
);
insert into memberInfo (
 memberNumber,codeNumber,age,gender,name,phone,birthday)
values(memberNumber.nextval,'ASF4DS',20,'����','����','010-555-5555','920412');

insert into memberInfo (
 memberNumber,codeNumber,age,gender,name,phone,birthday)
values(memberNumber.nextval,'4dFD4D',25,'����','���ٿ�','010-5145-1111','920156');

insert into memberInfo (
 memberNumber,codeNumber,age,gender,name,phone,birthday)
values(memberNumber.nextval,'4dFdasd',25,'����','�ճ���','010-5145-1111','920156');

insert into memberInfo (
 memberNumber,codeNumber,age,gender,name,phone,birthday)
values(memberNumber.nextval,'4dFfsd',25,'����','������','010-5145-1111','920156');

delete from memberInfo
where trim(name)='������';
Select  codeNumber from memberInfo
order by codeNumber;

 







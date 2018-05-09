Drop table foodManaging;

CREATE TABLE foodManaging--���İ������̺�
  ( country    VARCHAR2 (20) ,
    foodSerial VARCHAR2 (10) NOT NULL ,
    foodType   VARCHAR2 (10) ,
    foodName   VARCHAR2 (30) NOT NULL ,
    foodPrice  NUMBER (5) ); 
ALTER TABLE foodManaging ADD CONSTRAINT foodManaging_PK PRIMARY KEY ( foodSerial, foodName ) ;

SELECT * FROM foodManaging;

insert into foodManaging
values('�ѽ�','K001','�丮��','�κα�ġ',10000);
insert into foodManaging
values('�ѽ�','K002','����','��ū�ɰԿ�����',12000);
insert into foodManaging
values('�ѽ�','K003','����','����������',15000);
insert into foodManaging
values('�ѽ�','K004','����','��տ�¡���ع�����',10000);
insert into foodManaging
values('�ѽ�','K005','����','�����ߺ�����',15000);
insert into foodManaging
values('�ѽ�','K006','����','�ż���',25000);

insert into foodManaging
values('�Ͻ�','J001','Ƣ���','�ջ���Ƣ��',12000);
insert into foodManaging
values('�Ͻ�','J002','Ƣ���','ġ��',12000);
insert into foodManaging
values('�Ͻ�','J003','����','��ġ�����쵿',10000);
insert into foodManaging
values('�Ͻ�','J004','ȸ��','���ٶ���ȸ',30000);
insert into foodManaging
values('�Ͻ�','J005','���̷�','�޷α���',25000);
insert into foodManaging
values('�Ͻ�','J006','����','Ư��������̾߳�',10000);

insert into foodManaging
values('���','W001','Ƣ���','��밨��Ƣ��',12000);
insert into foodManaging
values('���','W002','����','�����Ҽ�������',13000);
insert into foodManaging
values('���','W003','Ƣ���','Ȳ��ġŲ',12000);
insert into foodManaging
values('���','W004','����','�����ٺ�ť',15000);

insert into foodManaging
values('�ַ�','t001','����','���̽�',3000);
insert into foodManaging
values('�ַ�','t002','����','ó��ó��',3000);
insert into foodManaging
values('�ַ�','t003','����','C1',3000);
insert into foodManaging
values('�ַ�','t004','����','��������',3000);

insert into foodManaging
values('�ַ�','t005','����','������500CC',3000);
insert into foodManaging
values('�ַ�','t006','����','������1000cc',6000);
insert into foodManaging
values('�ַ�','t007','����','�����500cc',4500);

update foodManaging
set foodPrice = 4000
where foodSerial = 't007';

insert into foodManaging
values('�ַ�','t008','Ź��','�����ɸ�',4000);
insert into foodManaging
values('�ַ�','t009','Ź��','�ܵ�����',4000);


select FOODNAME,FOODPRICE from foodManaging where FOODNAME = 'Ȳ��ġŲ';

왜 git에 안올라가지냐

delete from board; 
drop table board;
drop sequence seq_board_no;

select * from users;
select * from board;
select * from guestbook;

 create table board  (no number(10), 
	 				  title varchar2(20), 
	  				  content nvarchar2(2000),
	  				  reg_date date default sysdate,
	  				  hit number(10) default 0,
	 				  user_no number(20),
                      CONSTRAINT FK_USERS_NO FOREIGN KEY(USER_NO) REFERENCES USERS(NO),
	  				  primary key(no));
                            
create sequence seq_board_no start with 1 increment by 1;

insert into board values (seq_board_no.nextval, 
						  '안녕하세요', 
                          '안녕',
                          default,
                          default,
                          3);

select b.no, b.title, b.content, b.reg_date, b.hit
from board b, users u
where b.user_no = u.no;

update board set title = '안녕', 
             content = 'hi' 
where no = 1;

commit;   
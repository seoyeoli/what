create table just_user
(
    user_id varchar2(100) primary key,
    user_num varchar2(100),
    user_name varchar2(100),
    user_pw varchar2(2000),
    user_birthday varchar2(6),
    user_phone varchar2(11),
    user_visit date,
    user_auth char(2)
)

	insert into just_user(user_id, user_pw, user_name,user_auth)
	values('admin','5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5','관리자','AD');


create table just_board
(
    board_id varchar2(100) primary key,
    user_NUM varchar2(100),
    board_title varchar2(1000),
    board_body varchar2(2000),
    board_wrt_date date default sysdate,
    board_del_yn char(1) default 'N',
    board_old_id varchar2(100),
    board_notice char(1) default 'N'
)


create table just_reple
(
    reple_id varchar2(100),
    board_id varchar2(100),
    reple_old_id varchar2(100),
    reple_body varchar2(2000),
    user_id varchar2(100),
    reple_wrt_date date,
    reple_del_yn char(1) default 'N'
)


create table just_schedule
(
    sch_id varchar2(100) primary key,
    user_id varchar2(100),
    sch_day varchar2(100),
    sch_time varchar2(100),
    prog_id varchar2(100),
    sch_wrt_user_id varchar2(100)
)

	insert into just_schedule(
	    sch_id ,
	    user_id ,
	    sch_day ,
	    sch_time ,
	    prog_id ,
	    sch_wrt_user_id
	)
	values(
	    to_char(sysdate,'yyyymmddhh24miss')||'000',
	    'admin',
	    '20190620',
	    '1400',
	    '',
	    'admin'
	);




create table just_member(
    mem_id varchar2(100) primary key,
    user_num varchar2(100),
    mem_strday date,
    mem_endday date,
    mem_exp char(1) default 'N',
    mem_allpt varchar2(100),
    mem_usept varchar2(100),
    trainer_id varchar2(100)
)


create table just_code
(
    code_id char(2),
    code_name varchar2(100)
)

	insert into just_code( code_id , code_name) values('PT', 'PT');
	insert into just_code( code_id , code_name) values('YO', 'YOGA');
	insert into just_code( code_id , code_name) values('PI', '필라테스');
	insert into just_code( code_id , code_name) values('DA', '댄스');
	insert into just_code( code_id , code_name) values('TP', 'PT트레이너');
	insert into just_code( code_id , code_name) values('AD', '관리자');


create table just_program(
    prog_id varchar2(100),
    prog_type char(2),
    prog_trainer varchar2(100),
    prog_remark varchar2(2000),
    prog_limit number(3)
)

	insert into just_program (
	    PROG_ID,
	    PROG_TYPE,
	    PROG_TRAINER,
	    PROG_REMARK,
	    PROG_LIMIT)
	values(
	    'PT'||to_char(sysdate,'yyyymmdd')||'000',
	    'PT',
	    'admin',
	    '관리자 트레이너의 PT수업',
	    '999'
	)


create table just_prog_user
(
    prog_id varchar2(100),
    user_id varchar2(100)
)

--프로그램생성-> 유저할당(등록할 수 있는 스케줄 설정)-> 스케쥴생성

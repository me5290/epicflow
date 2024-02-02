# 데이터베이스
drop database if exists epicflow;
create database epicflow;
use epicflow;

# 회원테이블
drop table if exists member;
create table member(
	mno int auto_increment primary key,		-- 회원번호
    mid varchar(16) not null unique,		-- 회원아이디
    mpw varchar(13) not null				-- 회원비밀번호
);

insert into member(mid , mpw) values('aaa' , 'aaa123');
insert into member(mid , mpw) values('bbb' , 'bbb123');

# 캐릭터테이블
drop table if exists player;
create table player(
	pno int auto_increment primary key,		-- 플레이어 번호
    pname varchar(8) not null unique,		-- 플레이어 이름
    mhp int default 100,					-- 최대체력
    hp int default 100,						-- 체력
    mmp int default 50,						-- 최대마나
    mp int default 50,						-- 마나
    job int default 0,						-- 직업
    level int default 1,					-- 레벨
    exp int default 0,						-- 경험치
    money int default 0,					-- 돈
    statpoint int default 0,				-- 포인트
    power int default 10,					-- 공격력
    defence int default 0,					-- 방어력
    str int default 0,						-- 힘
    dex int default 0,						-- 민첩
    wis int default 0,						-- 지능
    eva int default 0,						-- 회피
    spd int default 0,						-- 속도
    mno int,								-- 회원번호
    foreign key(mno) references member(mno)
);

insert into player(pname) values('zl존민형');
insert into player(pname) values('아기준영');

update player set mno = 1 where pno = 1;
update player set mno = 2 where pno = 2;

select * from member;
select * from player;
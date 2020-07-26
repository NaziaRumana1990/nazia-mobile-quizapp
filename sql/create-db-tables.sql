create database mobilequizdemo;

create table applicationuser (
  id int auto_increment not null primary key,
  username varchar(32),
  password varchar(100)
);

create table game (
  id int auto_increment not null primary key,
  user_id int,
  game_id int,
  question_id int,
  optionNo int,
  islifelineused boolean,
  isanswercorrect boolean,
  answeredTimeInSeconds int
);
	
create table questions (
  id int auto_increment not null primary key,
  qvalue varchar(100));

create table answer (
  id int auto_increment not null primary key,
  question_id int,
  option_1 varchar(25),
  option_2 varchar(25),
  option_3 varchar(25),
  option_4 varchar(25),
  correctanswer int,
  foreign key (question_id) references questions(id)
  );
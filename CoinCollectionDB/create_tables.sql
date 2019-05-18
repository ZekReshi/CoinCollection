create table COIN (
  COIN_ID number(5) primary key,
  VALUE number(4),
  PRESERVATION number(1),
  YEAROFCOINAGE number(4),
  SOURCE_ID number(5),
  CURRENCY_ID number(5)
);

create table SOURCE (
  SOURCE_ID number(5) primary key,
  NAME varchar2(30)
);

create table CURRENCY (
  CURRENCY_ID number(5) primary key,
  NAME varchar2(10)
);

create table COLLECTOR (
  COLLECTOR_ID number(5) primary key,
  FIRSTNAME varchar2(20),
  LASTNAME varchar2(20)
);
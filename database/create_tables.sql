create table COIN (
  COIN_ID number(5) primary key,
  VALUE number(4),
  PRESERVATION number(1),
  YEAROFCOINAGE number(4),
  SOURCE_ID number(5),
  CURRENCY_ID number(5)
);

create table source (
  SOURCE_ID number(5) primary key,
  NAME varchar(30)
);

create table CURRENCY (
  CURRENCY_ID number(5) primary key,
  NAME varchar(10)
);
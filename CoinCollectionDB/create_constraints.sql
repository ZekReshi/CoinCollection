alter table COIN
add constraint COIN_SOURCE_FK
foreign key(SOURCE_ID)
references SOURCE(SOURCE_ID);

alter table COIN
add constraint COIN_CURRENCY_FK
foreign key(CURRENCY_ID)
references CURRENCY(currency_id);
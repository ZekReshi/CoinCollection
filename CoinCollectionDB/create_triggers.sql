create or replace trigger BI_COIN
  before insert
  on COIN
  for each row
begin
  if :NEW.COIN_ID is null
  then
    select SEQ_COIN.nextval
    into :NEW.COIN_ID
    from DUAL;
  end if;
end;

create or replace trigger BI_SOURCE
  before insert
  on SOURCE
  for each row
begin
  if :NEW.SOURCE_ID is null
  then
    select SEQ_SOURCE.nextval
    into :NEW.SOURCE_ID
    from DUAL;
  end if;
end;

create or replace trigger BI_CURRENCY
  before insert
  on CURRENCY
  for each row
begin
  if :NEW.CURRENCY_ID is null
  then
    select SEQ_CURRENCY.nextval
    into :NEW.CURRENCY_ID
    from DUAL;
  end if;
end;
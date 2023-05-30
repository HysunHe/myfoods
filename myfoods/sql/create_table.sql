CREATE TABLE ouser.j_onlineorder
  (id          VARCHAR2 (32) NOT NULL PRIMARY KEY,
   date_loaded TIMESTAMP (6) WITH TIME ZONE,
   oo_document VARCHAR2 (32767)
   CONSTRAINT oo_ensure_json CHECK (oo_document IS JSON));

CREATE TABLE ouser.j_onlinecustomerdata
  (id          VARCHAR2 (32) NOT NULL PRIMARY KEY,
   date_loaded TIMESTAMP (6) WITH TIME ZONE,
   oo_document VARCHAR2 (32767)
   CONSTRAINT ocd_ensure_json CHECK (oo_document IS JSON));


create table ouser.customerorder(Name varchar2(20),Phone varchar2(20),Location varchar2(20),Card varchar2(20));

create table ouser.FASTFOOD_TRANSACTIONS(
  TRANSACTION_ID NUMBER(10),
  LINE_ID NUMBER(10),
  NUMLINES NUMBER(10),
  PRODUCTID NUMBER(10),
  PRODUCT_NAME VARCHAR2(256),
  QUANTITY NUMBER(10),
  REVENUE NUMBER(10, 2)
);

Insert into OUSER.FASTFOOD_TRANSACTIONS (TRANSACTION_ID,LINE_ID,NUMLINES,PRODUCTID,PRODUCT_NAME,QUANTITY,REVENUE) values (3439,1,1,4,'steak',2,17.3);

CREATE TABLE PRODUCT
(
    id              bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            varchar(128),
    min_age         int,
    max_age         int,
    min_income      int,
    max_income      int,
    must_be_student bit
);



INSERT INTO PRODUCT ( name, min_age, max_age, min_income, max_income, must_be_student)
VALUES ( 'Current Account', 17, null, 0, null, false),
       ( 'Current Account Plus', 17, null, 40000, null, false),
       ( 'Junior Saver Account', null, 18, null, null, false),
       ( 'Student Account', 17, null, null, null, true),
       ( 'Senior Account', 64, null, null, null, false),
       ( 'Debit Card', 17, null, null, 12001, false),
       ( 'Credit Card', 17, null, 12000, null, false),
       ( 'Gold Credit Card', 17, null, 40000, null, false)
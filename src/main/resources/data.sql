CREATE TABLE product
(
    id              bigint,
    name            varchar(128),
    min_age         int,
    max_age         int,
    min_income      int,
    max_income      int,
    must_be_student bit
);



INSERT INTO product(id, name, min_age, max_age, min_income, max_income, must_be_student)
VALUES (1, 'Current Account', 17, null, 0, null, false),
       (2, 'Current Account Plus', 17, null, 40000, null, false),
       (3, 'Junior Saver Account', null, 18, null, null, false),
       (4, 'Student Account', null, 17, null, null, true),
       (5, 'Senior Account', 64, null, null, null, false),
       (6, 'Debit Card', 17, null, null, 12001, false),
       (7, 'Credit Card', 17, null, 12000, null, false),
       (7, 'Gold Credit Card', 17, null, 40000, null, false)
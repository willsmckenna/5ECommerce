CREATE TABLE seller
(
    id serial PRIMARY KEY,
    username varchar(50),
    firstname varchar(50),
    lastname varchar(50)

);

CREATE TABLE buyer
(
     id serial PRIMARY KEY,
     username varchar(50),
     firstname varchar(50),
     lastname varchar(50)
);

CREATE TABLE admin
(
     id serial PRIMARY KEY,
     username varchar(50),
     firstname varchar(50),
     lastname varchar(50)
);

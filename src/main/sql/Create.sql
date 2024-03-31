create table account(
    username varchar(100) primary key,
    password varchar(100) not null
);
create table userdetail(
    id int primary key auto_increment,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    email varchar(100) unique not null,
    accountUsername varchar(100) unique not null,
    CONSTRAINT userdetail_account FOREIGN KEY (accountUsername) REFERENCES account (username)
);



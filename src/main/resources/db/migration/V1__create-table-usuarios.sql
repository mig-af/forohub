
CREATE TABLE usuarios (
    id bigint not null auto_increment primary key,
    email varchar(100) not null unique,
    password varchar(100) not null
    
);




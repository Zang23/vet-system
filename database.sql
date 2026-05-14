create database agenda;

use agenda;

create table veterinario ( 
    id int AUTO_INCREMENT,
    nome char(100) not null,
    especialidade char(30) not null,
    crv char(100) not null,
    PRIMARY KEY(id)
);
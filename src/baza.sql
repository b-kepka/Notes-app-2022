 CREATE DATABASE if not exists notes;
use notes
CREATE TABLE if not exists Notatki (
    id integer PRIMARY KEY AUTO_INCREMENT,
    notatka text not null,
    data_wprowadzenia date not null,
    data_waznosci date not null
);
create table candidates
(
    id         int auto_increment
        primary key,
    name       varchar(100) not null,
    photo_path varchar(255) not null,
    jornada    varchar(50)  not null
);

create table voters
(
    id                    int auto_increment
        primary key,
    name                  varchar(100) not null,
    identification_number varchar(50)  not null,
    constraint identification_number
        unique (identification_number)
);

create table votes
(
    id           int auto_increment
        primary key,
    voter_id     int                                 not null,
    candidate_id int                                 not null,
    jornada      varchar(50)                         not null,
    vote_time    timestamp default CURRENT_TIMESTAMP null,
    constraint voter_id
        unique (voter_id, jornada),
    constraint votes_ibfk_1
        foreign key (voter_id) references voters (id),
    constraint votes_ibfk_2
        foreign key (candidate_id) references candidates (id)
);

create index candidate_id
    on votes (candidate_id);
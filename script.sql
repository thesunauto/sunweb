create table user
(
    username    varchar(255) not null
        primary key,
    datecreated datetime     not null,
    email       varchar(255) null,
    isdelete    int          not null,
    name        varchar(255) not null,
    password    varchar(255) not null,
    phone       varchar(255) null
);

create table category
(
    id          varchar(255) not null
        primary key,
    datecreated datetime     not null,
    detail      varchar(255) null,
    idparent    varchar(255) null,
    isdeleted   int          not null,
    metatitle   varchar(255) null,
    title       varchar(255) not null,
    usercreated varchar(255) not null,
    constraint fk_idparent_id
        foreign key (idparent) references category (id),
    constraint fk_usercreate_username
        foreign key (usercreated) references user (username)
);

create index fk__idx
    on category (usercreated);

create index fk_idparent_id_idx
    on category (idparent);

create table post
(
    id          varchar(255) not null
        primary key,
    context     varchar(255) not null,
    datecreated datetime     not null,
    datepuliced datetime     null,
    dateupdated datetime     null,
    idcategory  varchar(255) not null,
    image       varchar(255) null,
    isdeleted   int          not null,
    ispulic     int          not null,
    isshowindex int          not null,
    metatitle   varchar(255) null,
    title       varchar(255) not null,
    usercreated varchar(255) not null,
    constraint fk_idcategory_id
        foreign key (idcategory) references category (id),
    constraint fk_usercreated_username
        foreign key (usercreated) references user (username)
);

create index fk_category_id_idx
    on post (idcategory);

create index fk_usercreated_username_idx
    on post (usercreated);



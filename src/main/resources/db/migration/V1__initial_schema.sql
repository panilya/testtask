create table department
(
    id                    bigserial    not null,
    name                  varchar(255) not null,
    head_of_department_id bigint,
    primary key (id)
);

create table lecturer
(
    id     bigserial      not null,
    name   varchar(255)   not null,
    degree varchar(255)   not null,
    salary decimal(10, 2) not null,
    primary key (id)
);

create table department_lecturer
(
    department_id bigint not null,
    lecturer_id   bigint not null,
    primary key (department_id, lecturer_id)
);

alter table if exists department
    add constraint fk_lecturer_head_of_department
        foreign key (head_of_department_id) references lecturer (id);

alter table if exists department_lecturer
    add constraint fk_department_lecturer_department
        foreign key (department_id) references department (id);

alter table if exists department_lecturer
    add constraint fk_department_lecturer_lecturer
        foreign key (lecturer_id) references lecturer (id);

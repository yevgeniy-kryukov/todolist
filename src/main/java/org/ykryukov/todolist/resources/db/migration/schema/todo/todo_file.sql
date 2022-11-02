-- Table: todo.todo

-- DROP TABLE IF EXISTS todo.todo;

create table if not exists todo.todo_file
(
    todo_id integer not null,
    file_name character varying (50) not null,
    file_path character varying (500) not null,
    file_description character varying (255),
    id integer NOT NULL,
    constraint todo_files_pk primary key (id),
    constraint todo_files_fk foreign key (todo_id) references todo.todo (id),
    constraint todo_uq unique (todo_id, file_name, file_path)
)  tablespace pg_default;

comment on table todo.todo_file is 'Файл прикрепленный к делу';
comment on column todo.todo_file.todo_id is 'Идентификатор дела';
comment on column todo.todo_file.file_name is 'Наименование файла';
comment on column todo.todo_file.file_path is 'Относительный путь к файлу';
comment on column todo.todo_file.file_description is 'Описание файла';
comment on column todo.todo_file.id is 'Идентификатор';

ALTER TABLE IF EXISTS todo.todo_file
    OWNER to postgres;
    
create sequence todo.todo_file_seq;
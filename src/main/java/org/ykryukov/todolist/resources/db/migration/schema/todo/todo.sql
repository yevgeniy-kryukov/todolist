-- DROP TABLE IF EXISTS todo.todo;

CREATE TABLE IF NOT EXISTS todo.todo
(
    date_time_action timestamp without time zone NOT NULL,
    text_action character varying(1500) COLLATE pg_catalog."default" NOT NULL,
    is_done boolean NOT NULL DEFAULT false,
    id integer NOT NULL,
    CONSTRAINT todo_pk PRIMARY KEY (id)
)

TABLESPACE pg_default;

comment on table todo.todo is 'Дело (заметка)';
comment on column todo.todo.date_time_action is 'Дата и время запланированного дела';
comment on column todo.todo.text_action is 'Описание дела';
comment on column todo.todo.is_done is 'Отметка о выполнении дела';
comment on column todo.todo.id is 'Идентификатор';

ALTER TABLE IF EXISTS todo.todo
    OWNER to postgres;
    
CREATE SEQUENCE IF NOT EXISTS todo.todo_seq;
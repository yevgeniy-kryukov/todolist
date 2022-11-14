CREATE SCHEMA IF NOT EXISTS todo;
---------------------------------------------------
CREATE SEQUENCE IF NOT EXISTS todo.todo_file_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------------
CREATE SEQUENCE IF NOT EXISTS todo.todo_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
---------------------------------------------------
CREATE TABLE IF NOT EXISTS todo.todo
(
    date_time_action timestamp without time zone NOT NULL,
    text_action character varying(1500) COLLATE pg_catalog."default" NOT NULL,
    is_done boolean NOT NULL DEFAULT false,
    id integer NOT NULL,
    CONSTRAINT todo_pk PRIMARY KEY (id)
);

COMMENT ON TABLE todo.todo
    IS 'Дело (заметка)';

COMMENT ON COLUMN todo.todo.date_time_action
    IS 'Дата и время запланированного дела';

COMMENT ON COLUMN todo.todo.text_action
    IS 'Описание дела';

COMMENT ON COLUMN todo.todo.is_done
    IS 'Отметка о выполнении дела';

COMMENT ON COLUMN todo.todo.id
    IS 'Идентификатор';
--------------------------------------------------- 
CREATE TABLE IF NOT EXISTS todo.todo_file
(
    todo_id integer NOT NULL,
    file_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    file_path character varying(500) COLLATE pg_catalog."default" NOT NULL,
    file_description character varying(255) COLLATE pg_catalog."default",
    id integer NOT NULL,
    CONSTRAINT todo_files_pk PRIMARY KEY (id),
    CONSTRAINT todo_uq UNIQUE (todo_id, file_name, file_path),
    CONSTRAINT todo_files_fk FOREIGN KEY (todo_id)
        REFERENCES todo.todo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE todo.todo_file
    IS 'Файл прикрепленный к делу';

COMMENT ON COLUMN todo.todo_file.todo_id
    IS 'Идентификатор дела';

COMMENT ON COLUMN todo.todo_file.file_name
    IS 'Наименование файла';

COMMENT ON COLUMN todo.todo_file.file_path
    IS 'Относительный путь к файлу';

COMMENT ON COLUMN todo.todo_file.file_description
    IS 'Описание файла';

COMMENT ON COLUMN todo.todo_file.id
    IS 'Идентификатор';

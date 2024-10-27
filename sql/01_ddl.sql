CREATE SCHEMA app
    AUTHORIZATION postgres;

CREATE TABLE app.login
(

    login character varying,
    password character varying NOT NULL,
    fio character varying,
    birthday date,
    create_at timestamp NOT NULL,
    role character varying NOT NULL,
    CONSTRAINT login_pk PRIMARY KEY (login)
);

ALTER TABLE IF EXISTS app.login
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS app.message (
     id uuid,
     create_at timestamp NOT NULL,
     mail_from character varying NOT NULL,
     mail_to character varying NOT NULL,
     text character varying,
     CONSTRAINT message_pk PRIMARY KEY (id)
    );

ALTER TABLE IF EXISTS app.message
    OWNER to postgres;

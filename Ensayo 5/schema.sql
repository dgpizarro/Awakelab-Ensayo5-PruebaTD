-- Generado por Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   en:        2020-08-25 19:35:42 CLT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE comments (
    id      INTEGER NOT NULL,
    postid  INTEGER NOT NULL,
    name    VARCHAR2(250 CHAR) NOT NULL,
    email   VARCHAR2(100 CHAR) NOT NULL,
    body    VARCHAR2(1000 CHAR)
);

ALTER TABLE comments ADD CONSTRAINT comments_pk PRIMARY KEY ( id );

CREATE TABLE posts (
    id      INTEGER NOT NULL,
    userid  INTEGER NOT NULL,
    title   VARCHAR2(250 CHAR) NOT NULL,
    body    VARCHAR2(1000 CHAR)
);

ALTER TABLE posts ADD CONSTRAINT posts_pk PRIMARY KEY ( id );

CREATE TABLE users (
    id        INTEGER NOT NULL,
    name      VARCHAR2(50 CHAR) NOT NULL,
    username  VARCHAR2(20 CHAR) NOT NULL,
    email     VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( id );

ALTER TABLE comments
    ADD CONSTRAINT comments_posts_fk FOREIGN KEY ( postid )
        REFERENCES posts ( id );

ALTER TABLE posts
    ADD CONSTRAINT posts_users_fk FOREIGN KEY ( userid )
        REFERENCES users ( id );
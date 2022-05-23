CREATE DATABASE university
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE SCHEMA IF NOT EXISTS education
    AUTHORIZATION postgres;

CREATE TABLE education.groups (
    id BIGSERIAL NOT NULL,
    name VARCHAR PRIMARY KEY NOT NULL
);

CREATE TABLE education.students (
    id BIGSERIAL PRIMARY KEY  NOT NULL,
    name VARCHAR NOT NULL,
    age SMALLINT NOT NULL,
    score DOUBLE PRECISION,
    olympic_gamer BOOLEAN
);

CREATE TABLE education.groups_and_students (
    group_name VARCHAR NOT NULL,
    student_id BIGINT NOT NULL UNIQUE
);

ALTER TABLE
    education.groups_and_students
    ADD CONSTRAINT
        groups_and_students_group_name_groups_name
    FOREIGN KEY
        (group_name)
    REFERENCES
        education.groups(name) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE
    education.groups_and_students
    ADD CONSTRAINT
	groups_and_students_student_id_students_id
    FOREIGN KEY
        (student_id)
    REFERENCES
        education.students(id) ON DELETE CASCADE ON UPDATE CASCADE;
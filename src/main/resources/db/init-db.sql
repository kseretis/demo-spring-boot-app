-- This part is necessery if the database name is not declared in the docker-compose.yml
-- From here
-- Create user
--CREATE USER admin;

-- Check if the database exists before creating it
--SELECT 'CREATE DATABASE my_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'my_db');

--Grant all privileges to the user admin
--GRANT ALL PRIVILEGES ON DATABASE my_db TO admin;
-- To here

-- Create needed extensions
CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;

-- Create types
CREATE TYPE public.course_status AS ENUM (
    'FULL',
    'AVAILABLE'
);

CREATE TYPE public.user_role AS ENUM (
    'ADMIN',
    'USER'
);

-- Must have cast, for ENUM Java types to be stored in postgreSQL table
CREATE CAST (character varying AS public.user_role) WITH INOUT AS ASSIGNMENT;

-- Create tables
CREATE TABLE IF NOT EXISTS public.users (
    id integer NOT NULL,
    username character varying(64) NOT NULL,
    firstname character varying(64) NOT NULL,
    lastname character varying(64) NOT NULL,
    password character varying NOT NULL,
    role public.user_role DEFAULT 'USER'::public.user_role NOT NULL
);

CREATE TABLE IF NOT EXISTS public.students (
    student_id uuid NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    date_of_birth date NOT NULL,
    class_year integer DEFAULT 1 NOT NULL,
    grade real DEFAULT 0 NOT NULL,
    supervisor_id uuid,
    full_name character varying(255)
);

CREATE TABLE IF NOT EXISTS public.professors (
    professor_id uuid NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    date_of_birth date NOT NULL,
    title character varying(4) NOT NULL,
    teaching_courses integer DEFAULT 0 NOT NULL,
    full_name character varying(255)
);

CREATE TABLE IF NOT EXISTS public.professors_students (
    professor_professor_id uuid NOT NULL,
    students_student_id uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS public.courses (
    course_id uuid NOT NULL,
    course_name character varying(255) NOT NULL,
    covered_seats integer NOT NULL,
    max_seats integer DEFAULT 15 NOT NULL,
    professor_id uuid,
    status character varying(20) DEFAULT 'AVAILABLE'::public.course_status NOT NULL
);

CREATE TABLE IF NOT EXISTS public.course_seats (
    id integer NOT NULL,
    course_name character varying(255) NOT NULL,
    course_id uuid NOT NULL,
    professor_id uuid NOT NULL,
    student_first_name character varying(255) NOT NULL,
    student_last_name character varying(255) NOT NULL,
    student_id uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS public.supervising (
    id integer NOT NULL,
    supervisor_first_name character varying(255) NOT NULL,
    supervisor_last_name character varying(255) NOT NULL,
    supervisor_id uuid NOT NULL,
    student_first_name character varying(255) NOT NULL,
    student_last_name character varying(255) NOT NULL,
    student_id uuid NOT NULL
);

CREATE TABLE IF NOT EXISTS public.professors_courses (
    professor_professor_id uuid NOT NULL,
    courses_course_id uuid NOT NULL
);

-- Create sequences
CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.users_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.course_seats_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE  public.supervising_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.supervising_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--update tables, add constraints

ALTER TABLE ONLY public.course_seats ALTER COLUMN id SET DEFAULT nextval('public.course_seats_id_seq'::regclass);

ALTER TABLE ONLY public.supervising ALTER COLUMN id SET DEFAULT nextval('public.supervising_id_seq'::regclass);

ALTER TABLE ONLY public.course_seats
    ADD CONSTRAINT course_seats_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (course_id);

ALTER TABLE ONLY public.professors
    ADD CONSTRAINT professors_pkey PRIMARY KEY (professor_id);

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (student_id);

ALTER TABLE ONLY public.supervising
    ADD CONSTRAINT supervising_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.professors_students
    ADD CONSTRAINT uk_4436rsgfsjjkwrfq4hm30mai2 UNIQUE (students_student_id);

ALTER TABLE ONLY public.professors_courses
    ADD CONSTRAINT uk_kdo35j7vjdvhufejbas9djpvt UNIQUE (courses_course_id);

ALTER TABLE ONLY public.professors_courses
    ADD CONSTRAINT fk2q2996o1qxsgqyfmndt5m401n FOREIGN KEY (professor_professor_id) REFERENCES public.professors(professor_id);

ALTER TABLE ONLY public.course_seats
    ADD CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES public.courses(course_id);

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT fk_professor FOREIGN KEY (professor_id) REFERENCES public.professors(professor_id);

ALTER TABLE ONLY public.course_seats
    ADD CONSTRAINT fk_professor FOREIGN KEY (professor_id) REFERENCES public.professors(professor_id);

ALTER TABLE ONLY public.supervising
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES public.students(student_id);

ALTER TABLE ONLY public.supervising
    ADD CONSTRAINT fk_supervisor FOREIGN KEY (supervisor_id) REFERENCES public.professors(professor_id);

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fk_supervisor_id FOREIGN KEY (supervisor_id) REFERENCES public.professors(professor_id);

ALTER TABLE ONLY public.professors_students
    ADD CONSTRAINT fkaj6hj5dol61ma1daeqgdvmyon FOREIGN KEY (students_student_id) REFERENCES public.students(student_id);

ALTER TABLE ONLY public.professors_students
    ADD CONSTRAINT fkpjhsp8jsptx1qkobh01d8d0o FOREIGN KEY (professor_professor_id) REFERENCES public.professors(professor_id);

ALTER TABLE ONLY public.professors_courses
    ADD CONSTRAINT fkr9qyb841uemttpoms0aewphwd FOREIGN KEY (courses_course_id) REFERENCES public.courses(course_id);

ALTER TABLE ONLY public.course_seats
    ADD CONSTRAINT fl_student FOREIGN KEY (student_id) REFERENCES public.students(student_id);

--insert values
INSERT INTO public.users
VALUES
    (1,	'admin', 'admin', 'TheAdmin', '$2a$06$8g4dV96VIGTcxakqeCv3XueM7DbyfIJGXdZcSG3GnkkAUy8zKZe86', 'ADMIN');

INSERT INTO public.professors
VALUES
    ('db474920-1828-4405-9779-6c15dc0aab66', 'Nia', 'Higgins', '1982-06-24', 'Ms', 1, NULL),
    ('c275974c-507e-44e9-a449-114916a51a41', 'Mckenzie', 'Hardin', '1971-07-04', 'Mr', 1, NULL),
    ('0b206a6b-30de-4f54-81be-50d72503e863', 'Nicolas', 'Waters', '1981-04-29', 'Mr', 2, NULL),
    ('da1038ed-d0ce-4595-841c-461152bd5363', 'Ryan', 'Wilkins', '1976-02-14', 'Mr', 2, NULL),
    ('a7a21ca2-0eee-46a1-adce-2cfcdec8f68f', 'Angela ', 'Ferguson', '1990-03-19', 'Ms', 0, NULL);

INSERT INTO public.students
VALUES
    ('a62c069b-de22-4ee5-901b-ab642be8574b', 'Amna', 'Villarreal', '1997-10-09', 3, 9.1, 'db474920-1828-4405-9779-6c15dc0aab66', NULL),
    ('31dd0a37-4f4f-4acf-9166-b43056f184ed', 'Jaydon', 'Blake', '1995-06-15', 4, 7.2, 'c275974c-507e-44e9-a449-114916a51a41', NULL),
    ('fcc1aa66-c03c-4479-b82c-98794771c9f6', 'Asia', 'Brock', '1997-12-10', 5, 6, 'da1038ed-d0ce-4595-841c-461152bd5363', NULL),
    ('9f93c079-0616-4633-858f-6576e1cf6b28', 'Cai', 'Campbell', '1997-12-17', 5, 6, 'da1038ed-d0ce-4595-841c-461152bd5363', NULL),
    ('cfedd844-0274-4cb3-9615-faaa0ee808ad', 'Mateo', 'Garcia', '1998-10-09', 2, 7.1, 'a7a21ca2-0eee-46a1-adce-2cfcdec8f68f', NULL),
    ('6be839de-6e0e-40ac-86c4-91c95c305f81', 'Kaylum', 'Randolph', '1999-11-01', 4, 4.5, 'c275974c-507e-44e9-a449-114916a51a41', NULL),
    ('83b03c43-7aea-45d2-bb42-a0eaa9cd5620', 'Kaylum', 'Randolph', '1999-11-01', 5, 4.5, 'c275974c-507e-44e9-a449-114916a51a41', NULL),
    ('9cc0babf-0715-4660-8f50-2b96ff7eb197', 'Petros', 'Mantalos', '1996-11-01', 6, 5.7, 'c275974c-507e-44e9-a449-114916a51a41', NULL);

INSERT INTO public.courses
VALUES
    ('cc36cd3b-b141-41e9-9739-ad1dd35f0668', 'C++', 18, 29, 'a7a21ca2-0eee-46a1-adce-2cfcdec8f68f', 'AVAILABLE'),
    ('bcb33e94-d605-411e-a39d-1e7c344c9db5', 'Agile', 23, 30, 'db474920-1828-4405-9779-6c15dc0aab66', 'AVAILABLE'),
    ('9cdce704-a313-4b47-980c-fa823523587e', 'Data Structures', 4, 23, 'da1038ed-d0ce-4595-841c-461152bd5363', 'AVAILABLE'),
    ('cb74bb6b-6a09-42da-b033-dbb345f4c9d9', 'C++', 0, 30, '0b206a6b-30de-4f54-81be-50d72503e863', 'AVAILABLE'),
    ('d330e8e5-3d81-4bd5-999c-14890bc3ad06', 'Java', 34, 34, 'c275974c-507e-44e9-a449-114916a51a41', 'FULL'),
    ('6f06868e-6a93-4123-bc36-78830d38226f', '.Net', 10, 30, '0b206a6b-30de-4f54-81be-50d72503e863', 'AVAILABLE'),
    ('ceea88c4-5bcc-4b87-abf1-82bfa49430e2', 'Mongo DB', 1, 30, 'da1038ed-d0ce-4595-841c-461152bd5363', 'AVAILABLE'),
    ('0b0371b6-06f8-4eb9-8686-8a4a740bc860', 'Python', 0, 25, 'a7a21ca2-0eee-46a1-adce-2cfcdec8f68f', 'AVAILABLE'),
    ('2d7818bb-9971-44ae-b74e-54bf5d680c59', 'Scala', 0, 30, 'a7a21ca2-0eee-46a1-adce-2cfcdec8f68f', 'AVAILABLE');

INSERT INTO public.course_seats
VALUES
    (1, 'Java', 'd330e8e5-3d81-4bd5-999c-14890bc3ad06', 'c275974c-507e-44e9-a449-114916a51a41', 'Jaydon', 'Blake', '31dd0a37-4f4f-4acf-9166-b43056f184ed'),
    (2, 'Data Structures', '9cdce704-a313-4b47-980c-fa823523587e', 'da1038ed-d0ce-4595-841c-461152bd5363', 'Amna', 'Villarreal', 'a62c069b-de22-4ee5-901b-ab642be8574b');

INSERT INTO public.supervising
VALUES
    (1, 'Mckenzie', 'Hardin', 'c275974c-507e-44e9-a449-114916a51a41', 'Jaydon', 'Blake', '31dd0a37-4f4f-4acf-9166-b43056f184ed'),
    (2, 'Nia', 'Higgins', 'db474920-1828-4405-9779-6c15dc0aab66', 'Amna', 'Villarreal', 'a62c069b-de22-4ee5-901b-ab642be8574b'),
    (3, 'Mckenzie', 'Hardin', 'c275974c-507e-44e9-a449-114916a51a41', 'Kaylum', 'Randolph', '6be839de-6e0e-40ac-86c4-91c95c305f81');

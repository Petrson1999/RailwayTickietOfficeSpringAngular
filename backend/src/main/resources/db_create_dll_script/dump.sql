--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- Name: role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.role AS ENUM (
    'admin',
    'user'
);


ALTER TYPE public.role OWNER TO postgres;

--
-- Name: status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.status AS ENUM (
    'free',
    'paid',
    'reserved'
);


ALTER TYPE public.status OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login text,
    password text,
    role text,
    name text,
    surname text,
    funds double precision,
    CONSTRAINT users_funds_check CHECK ((funds > (0)::double precision))
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: User_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."User_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."User_id_seq" OWNER TO postgres;

--
-- Name: User_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."User_id_seq" OWNED BY public.users.id;


--
-- Name: flights; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flights (
    id integer NOT NULL,
    departure_station_id integer,
    arrival_station_id integer,
    departure_time timestamp without time zone,
    arrival_time timestamp without time zone,
    cost double precision,
    name text,
    train_id integer
);


ALTER TABLE public.flights OWNER TO postgres;

--
-- Name: flights_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.flights_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.flights_id_seq OWNER TO postgres;

--
-- Name: flights_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.flights_id_seq OWNED BY public.flights.id;


--
-- Name: locomotives; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.locomotives (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.locomotives OWNER TO postgres;

--
-- Name: locomotives_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.locomotives ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.locomotives_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: luggage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.luggage (
    id integer NOT NULL,
    ticket_id integer
);


ALTER TABLE public.luggage OWNER TO postgres;

--
-- Name: luggage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.luggage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.luggage_id_seq OWNER TO postgres;

--
-- Name: luggage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.luggage_id_seq OWNED BY public.luggage.id;


--
-- Name: seats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.seats (
    id integer NOT NULL,
    wagon_id integer,
    place_number integer
);


ALTER TABLE public.seats OWNER TO postgres;

--
-- Name: seats_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seats_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seats_id_seq OWNER TO postgres;

--
-- Name: seats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.seats_id_seq OWNED BY public.seats.id;


--
-- Name: stations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stations (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.stations OWNER TO postgres;

--
-- Name: stations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.stations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stations_id_seq OWNER TO postgres;

--
-- Name: stations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.stations_id_seq OWNED BY public.stations.id;


--
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id integer NOT NULL,
    flight_id integer,
    user_id integer,
    cost double precision,
    seat_id integer
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tickets_id_seq OWNER TO postgres;

--
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;


--
-- Name: trains; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.trains (
    id integer NOT NULL,
    name text,
    locomotive_id integer
);


ALTER TABLE public.trains OWNER TO postgres;

--
-- Name: trains_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.trains_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trains_id_seq OWNER TO postgres;

--
-- Name: trains_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.trains_id_seq OWNED BY public.trains.id;


--
-- Name: wagon_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wagon_types (
    id integer NOT NULL,
    number_of_seats integer,
    comfort integer,
    name text
);


ALTER TABLE public.wagon_types OWNER TO postgres;

--
-- Name: wagon_types_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wagon_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wagon_types_id_seq OWNER TO postgres;

--
-- Name: wagon_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wagon_types_id_seq OWNED BY public.wagon_types.id;


--
-- Name: wagons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wagons (
    id integer NOT NULL,
    train_id integer,
    type_id integer,
    name text
);


ALTER TABLE public.wagons OWNER TO postgres;

--
-- Name: wagons_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wagons_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wagons_id_seq OWNER TO postgres;

--
-- Name: wagons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.wagons_id_seq OWNED BY public.wagons.id;


--
-- Name: flights id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights ALTER COLUMN id SET DEFAULT nextval('public.flights_id_seq'::regclass);


--
-- Name: luggage id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.luggage ALTER COLUMN id SET DEFAULT nextval('public.luggage_id_seq'::regclass);


--
-- Name: seats id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seats ALTER COLUMN id SET DEFAULT nextval('public.seats_id_seq'::regclass);


--
-- Name: stations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stations ALTER COLUMN id SET DEFAULT nextval('public.stations_id_seq'::regclass);


--
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


--
-- Name: trains id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trains ALTER COLUMN id SET DEFAULT nextval('public.trains_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public."User_id_seq"'::regclass);


--
-- Name: wagon_types id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagon_types ALTER COLUMN id SET DEFAULT nextval('public.wagon_types_id_seq'::regclass);


--
-- Name: wagons id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagons ALTER COLUMN id SET DEFAULT nextval('public.wagons_id_seq'::regclass);


--
-- Data for Name: flights; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flights (id, departure_station_id, arrival_station_id, departure_time, arrival_time, cost, name, train_id) FROM stdin;
1	1	2	2020-10-02 11:20:18.674	2020-10-03 11:20:18.674	10	st1 - st2	9
2	2	1	2020-11-10 11:20:18.674	2020-11-11 11:20:18.674	20	st2-st1	21
3	3	4	2020-12-10 11:20:18.674	2020-01-01 11:20:18.674	50	st3-st4	9
999	1	2	2018-12-11 00:48:25.893	2018-12-12 00:48:28.17	50	st1 - st2	9
4	1	4	2019-12-14 00:00:00	2019-12-15 00:00:00	15	Kiev - Shostcka	21
\.


--
-- Data for Name: locomotives; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.locomotives (id, name) FROM stdin;
0	locomotive0
3	locomotive3
4	locomotive4
5	locomotive5
6	locomotive6
7	locomotive7
8	locomotive8
9	locomotive9
10	locomotive10
\.


--
-- Data for Name: luggage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.luggage (id, ticket_id) FROM stdin;
\.


--
-- Data for Name: seats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.seats (id, wagon_id, place_number) FROM stdin;
32	999	3
33	999	4
31	999	2
35	999	1
34	999	5
47	5	1
48	5	2
49	5	3
50	5	4
51	5	5
52	5	6
53	5	7
54	5	8
55	5	9
56	5	10
57	5	11
58	5	12
59	5	13
60	5	14
61	5	15
62	5	16
63	5	17
64	5	18
65	5	19
66	5	20
67	5	21
68	5	22
69	5	23
70	5	24
71	5	25
72	5	26
73	5	27
74	5	28
75	5	29
76	5	30
77	5	31
78	5	32
79	5	33
80	5	34
81	5	35
82	5	36
83	5	37
84	5	38
85	5	39
86	5	40
\.


--
-- Data for Name: stations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stations (id, name) FROM stdin;
4	Kharkov
2	Odessa
3	Lviv
1	Kiev
6	Tsdfdsfsf
7	Ternopil
8	trainNew
9	newTrain
\.


--
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (id, flight_id, user_id, cost, seat_id) FROM stdin;
2	1	7	10	34
8	1	3	10	33
23	1	3	10	32
3	999	3	50	32
24	1	18	10	47
26	1	18	10	48
27	1	18	10	56
\.


--
-- Data for Name: trains; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.trains (id, name, locomotive_id) FROM stdin;
21	train2	4
22	train3	5
23	train4	6
24	train5	7
9	train1	4
25	train100	0
26	newTrain	4
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, login, password, role, name, surname, funds) FROM stdin;
8	dsfsdfsd@gmail.com	123456	USER	уцкцу	цукцу	100
3	login2@gmail.com	123456	USER	name2	lastName2	70
17	admin@gmail.com	123456	ADMIN	admin	admin	9999
7	vovaka@gmail.com	123456	USER	name	sutname	100
19	login1@gmail.com	$2a$10$CHMIGq3gWce8BDhqgzFACuN0Adoo2AmrXpu3E12FXvNHGKRoWzG26	USER	vova	voova	100
20	login3@gmail.com	$2a$10$cjytDvpmkzLGi.PcYlVLwe6Jxxv3QMm7HJ/Gs.h/ULH4rm1vgnmCa	USER	full_name	Surname	100
21	login4@gmail.com	$2a$10$aK3F8B1uoDyBzBkdxiuENuMiZPhFHLrzrZp3OuXuusp8qLFHlgIxu	USER	full_name	Surname	100
18	login	$2a$10$BwGDfqDrq53GcYywI/FJtuDNFz3PtQWJ2XmDfI7vSnDrBNdhsHEGu	USER	name	surname	80
\.


--
-- Data for Name: wagon_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wagon_types (id, number_of_seats, comfort, name) FROM stdin;
1	50	3	second class
2	40	5	econom class
\.


--
-- Data for Name: wagons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wagons (id, train_id, type_id, name) FROM stdin;
999	9	2	wagon2
5	9	2	newWagon
\.


--
-- Name: User_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."User_id_seq"', 21, true);


--
-- Name: flights_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.flights_id_seq', 4, true);


--
-- Name: locomotives_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.locomotives_id_seq', 23, true);


--
-- Name: luggage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.luggage_id_seq', 9, true);


--
-- Name: seats_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seats_id_seq', 86, true);


--
-- Name: stations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.stations_id_seq', 9, true);


--
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_seq', 27, true);


--
-- Name: trains_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.trains_id_seq', 26, true);


--
-- Name: wagon_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wagon_types_id_seq', 3, true);


--
-- Name: wagons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wagons_id_seq', 5, true);


--
-- Name: wagon_types class_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagon_types
    ADD CONSTRAINT class_pk PRIMARY KEY (id);


--
-- Name: flights flights_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_pk PRIMARY KEY (id);


--
-- Name: locomotives locomotives_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.locomotives
    ADD CONSTRAINT locomotives_pkey PRIMARY KEY (id);


--
-- Name: luggage luggage_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.luggage
    ADD CONSTRAINT luggage_pk PRIMARY KEY (id);


--
-- Name: seats seats_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_pk PRIMARY KEY (id);


--
-- Name: stations stations_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stations
    ADD CONSTRAINT stations_pk PRIMARY KEY (id);


--
-- Name: tickets tickets_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pk PRIMARY KEY (id);


--
-- Name: trains trains_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trains
    ADD CONSTRAINT trains_pkey PRIMARY KEY (id);


--
-- Name: users user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: wagons wagons_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagons
    ADD CONSTRAINT wagons_pk PRIMARY KEY (id);


--
-- Name: flights flights_stations__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_stations__fk FOREIGN KEY (departure_station_id) REFERENCES public.stations(id);


--
-- Name: flights flights_stations__fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_stations__fk_2 FOREIGN KEY (arrival_station_id) REFERENCES public.stations(id);


--
-- Name: flights flights_trains__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_trains__fk FOREIGN KEY (train_id) REFERENCES public.trains(id);


--
-- Name: luggage luggage_tickets__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.luggage
    ADD CONSTRAINT luggage_tickets__fk FOREIGN KEY (ticket_id) REFERENCES public.tickets(id);


--
-- Name: seats seats_wagons__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_wagons__fk FOREIGN KEY (wagon_id) REFERENCES public.wagons(id);


--
-- Name: tickets tickets_flights__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_flights__fk FOREIGN KEY (flight_id) REFERENCES public.flights(id);


--
-- Name: tickets tickets_seats__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_seats__fk FOREIGN KEY (seat_id) REFERENCES public.seats(id);


--
-- Name: tickets tickets_users__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_users__fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: trains trains_locomotives__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.trains
    ADD CONSTRAINT trains_locomotives__fk FOREIGN KEY (locomotive_id) REFERENCES public.locomotives(id) ON UPDATE SET NULL;


--
-- Name: wagons wagons_class__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagons
    ADD CONSTRAINT wagons_class__fk FOREIGN KEY (type_id) REFERENCES public.wagon_types(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: wagons wagons_trains__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wagons
    ADD CONSTRAINT wagons_trains__fk FOREIGN KEY (train_id) REFERENCES public.trains(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--


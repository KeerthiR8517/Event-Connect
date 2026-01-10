
PostgreSQL


Schema - events
  
CREATE TABLE IF NOT EXISTS event.users
(
    id integer NOT NULL DEFAULT nextval('event.users_id_seq'::regclass),
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username)
)


CREATE TABLE IF NOT EXISTS event.events
(
    id integer NOT NULL DEFAULT nextval('event.events_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    location character varying(100) COLLATE pg_catalog."default",
    event_date timestamp without time zone,
    total_tickets integer,
    available_tickets integer,
    CONSTRAINT events_pkey PRIMARY KEY (id)
)


CREATE TABLE IF NOT EXISTS event.bookings
(
    id integer NOT NULL DEFAULT nextval('event.bookings_id_seq'::regclass),
    event_id bigint,
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    tickets integer NOT NULL,
    booked_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT bookings_pkey PRIMARY KEY (id),
    CONSTRAINT bookings_event_id_fkey FOREIGN KEY (event_id)
        REFERENCES public.events (id) MATCH SIMPLE
  )

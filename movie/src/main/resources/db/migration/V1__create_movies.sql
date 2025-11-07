CREATE TABLE IF NOT EXISTS public.movies
(
    movie_id uuid NOT NULL PRIMARY KEY ,
    actors character varying(255),
    age_limit integer NOT NULL,
    budget character varying(32),
    country character varying(50),
    description character varying(300),
    director character varying(255),
    fees character varying(32),
    image_url character varying(2000),
    lasting integer NOT NULL,
    title character varying(100),
    film_year integer NOT NULL
);
CREATE TABLE IF NOT EXISTS public.genres
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(32)
);
CREATE TABLE IF NOT EXISTS public.movie_genres
(
    movie_id uuid NOT NULL REFERENCES public.movies (movie_id) ON DELETE CASCADE,
    genre_id uuid NOT NULL REFERENCES public.genres (id) ON DELETE CASCADE
);





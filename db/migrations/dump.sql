

CREATE TABLE public.message (
    author_id bigint,
    id bigint NOT NULL,
    filename character varying(255),
    tag character varying(255),
    text character varying(255)
);


ALTER TABLE public.message OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 227950)
-- Name: message_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.message_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_seq OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 227959)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    roles character varying(255),
    CONSTRAINT user_role_roles_check CHECK (((roles)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 162239)
-- Name: user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_seq OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 227963)
-- Name: usr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usr (
    active boolean NOT NULL,
    id bigint NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.usr OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 227951)
-- Name: usr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usr_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usr_seq OWNER TO postgres;

--
-- TOC entry 3176 (class 2606 OID 227958)
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 3178 (class 2606 OID 227969)
-- Name: usr usr_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT usr_pkey PRIMARY KEY (id);


--
-- TOC entry 3180 (class 2606 OID 227975)
-- Name: user_role fkfpm8swft53ulq2hl11yplpr5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkfpm8swft53ulq2hl11yplpr5 FOREIGN KEY (user_id) REFERENCES public.usr(id);


--
-- TOC entry 3179 (class 2606 OID 227970)
-- Name: message fkqhhiq2fjqs0a1cgrg9bueu7ab; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkqhhiq2fjqs0a1cgrg9bueu7ab FOREIGN KEY (author_id) REFERENCES public.usr(id);


-- Completed on 2024-12-02 19:21:30

--
-- PostgreSQL database dump complete
--


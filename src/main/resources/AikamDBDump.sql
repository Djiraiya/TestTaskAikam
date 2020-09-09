--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2020-09-09 21:46:48

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 32768)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    customer_id integer NOT NULL,
    firstname character varying(100) NOT NULL,
    lastname character varying(100) NOT NULL
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 32773)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id integer NOT NULL,
    name character varying(100) NOT NULL,
    price numeric(18,2) NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 32776)
-- Name: purchases; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchases (
    purchases_id integer NOT NULL,
    customer_id integer NOT NULL,
    product_id integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.purchases OWNER TO postgres;

--
-- TOC entry 2827 (class 0 OID 32768)
-- Dependencies: 202
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers VALUES (1, 'Иван', 'Иванов');
INSERT INTO public.customers VALUES (2, 'Александр', 'Иванов');
INSERT INTO public.customers VALUES (3, 'Иван', 'Александров');
INSERT INTO public.customers VALUES (4, 'Дмитрий', 'Дымов');
INSERT INTO public.customers VALUES (5, 'Георгий', 'Петров');
INSERT INTO public.customers VALUES (6, 'Аркадий', 'Зимний');
INSERT INTO public.customers VALUES (7, 'Кирилл', 'Шаповалов');
INSERT INTO public.customers VALUES (8, 'Никита', 'Шевчук');
INSERT INTO public.customers VALUES (9, 'Семен', 'Абабков');
INSERT INTO public.customers VALUES (10, 'Екатерина', 'Лескова');


--
-- TOC entry 2828 (class 0 OID 32773)
-- Dependencies: 203
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products VALUES (1, 'Минеральная вода', 100.00);
INSERT INTO public.products VALUES (2, 'Хлеб', 30.00);
INSERT INTO public.products VALUES (3, 'Колбаса', 250.00);
INSERT INTO public.products VALUES (4, 'Сметана', 60.00);
INSERT INTO public.products VALUES (5, 'Молоко', 55.00);
INSERT INTO public.products VALUES (6, 'Сыр', 200.00);
INSERT INTO public.products VALUES (7, 'Йогурт', 80.00);
INSERT INTO public.products VALUES (8, 'Макароны', 90.00);
INSERT INTO public.products VALUES (9, 'Сок', 120.00);
INSERT INTO public.products VALUES (10, 'Кофе', 300.00);


--
-- TOC entry 2829 (class 0 OID 32776)
-- Dependencies: 204
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchases VALUES (1, 1, 1, '2020-09-05');
INSERT INTO public.purchases VALUES (2, 1, 3, '2020-09-03');
INSERT INTO public.purchases VALUES (3, 3, 2, '2020-09-01');
INSERT INTO public.purchases VALUES (4, 4, 5, '2020-09-02');
INSERT INTO public.purchases VALUES (5, 5, 6, '2020-09-01');
INSERT INTO public.purchases VALUES (6, 7, 2, '2020-09-04');
INSERT INTO public.purchases VALUES (7, 8, 7, '2020-09-05');
INSERT INTO public.purchases VALUES (8, 4, 1, '2020-09-05');
INSERT INTO public.purchases VALUES (9, 6, 10, '2020-09-02');
INSERT INTO public.purchases VALUES (10, 4, 9, '2020-09-03');
INSERT INTO public.purchases VALUES (11, 1, 1, '2020-09-05');
INSERT INTO public.purchases VALUES (12, 4, 9, '2020-09-02');


--
-- TOC entry 2694 (class 2606 OID 32772)
-- Name: customers Customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT "Customers_pkey" PRIMARY KEY (customer_id);


--
-- TOC entry 2696 (class 2606 OID 32782)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 2698 (class 2606 OID 32780)
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (purchases_id);


--
-- TOC entry 2699 (class 2606 OID 32783)
-- Name: purchases fk_purchases_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT fk_purchases_customer FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) NOT VALID;


--
-- TOC entry 2700 (class 2606 OID 32788)
-- Name: purchases fk_purchases_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT fk_purchases_product FOREIGN KEY (product_id) REFERENCES public.products(product_id) NOT VALID;


-- Completed on 2020-09-09 21:46:48

--
-- PostgreSQL database dump complete
--


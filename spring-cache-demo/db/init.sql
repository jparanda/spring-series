CREATE TABLE customer (
    id int PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    dni character(225) NOT NULL,
    email character varying(255) NOT NULL,
    CONSTRAINT customer_dni_unique UNIQUE (dni)
);

CREATE SEQUENCE customer_id_seq
START 1
INCREMENT 1
MINVALUE 1
OWNED BY customer.id;

INSERT INTO customer (id, first_name, last_name, dni, email) VALUES
(nextval('customer_id_seq'), 'Juan Pablo', 'Aranda Galvis', '91111628' ,'juan.aranda.galvis@gmail.com'),
(nextval('customer_id_seq'), 'Cristina Maria', 'Tellez Manrique', '37749843', 'tellez@gmail.com'),
(nextval('customer_id_seq'), 'Maria Catalina', 'Aranda Tellez', '123456', 'catalina@gmail.com');
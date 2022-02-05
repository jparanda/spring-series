CREATE TABLE customer (
    id int PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL
);

CREATE SEQUENCE customer_id_seq
START 1
INCREMENT 1
MINVALUE 1
OWNED BY customer.id;

INSERT INTO customer (id, first_name, last_name, email) VALUES
(1, 'Juan Pablo', 'Aranda Galvis', 'juan.aranda.galvis@gmail.com'),
(2, 'Cristina Maria', 'Tellez Manrique', 'tellez@gmail.com'),
(3, 'Maria Catalina', 'Aranda Tellez', 'catalina@gmail.com');
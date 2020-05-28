CREATE TABLE users(
    id          serial PRIMARY KEY,
    user_name    varchar(50),
    password    varchar(50)
);
CREATE TABLE seller(id serial PRIMARY KEY, user_id integer REFERENCES users(id));

CREATE TABLE buyer(
    id          serial PRIMARY KEY,
    user_id     integer,
    cart_id     integer,
    order_id    integer
);

CREATE TABLE admin(adminid serial PRIMARY KEY, name varchar(50));

CREATE TABLE products(
    id          serial PRIMARY KEY,
    name        varchar(50),
    price       float(10),
    quantity    integer,
    description varchar(250),
    image       bytea,
    seller_id   integer REFERENCES seller(id)
);

CREATE TABLE orders(
    id          integer PRIMARY KEY,
    order_total float(20),
    product_id  integer REFERENCES products(id),
    buyer_id    integer REFERENCES buyer(id)
);

CREATE TABLE shoppingcart(
    id          integer PRIMARY KEY,
    product_id  integer REFERENCES products(id),
    buyer_id    integer REFERENCES buyer(id)
);

CREATE TABLE paymentinfo(
    id          serial PRIMARY KEY,
    card_number integer,
    cvv         integer,
    exp_date    integer,
    user_id     integer REFERENCES users(id)
);

ALTER TABLE buyer ADD CONSTRAINT constraint_fk0 FOREIGN KEY (cart_id) REFERENCES shopping_cart(id);
ALTER TABLE buyer ADD CONSTRAINT constraint_fk1 FOREIGN KEY (order_id) REFERENCES orders(id);

CREATE TABLE shippingaddress(
    id              serial PRIMARY KEY,
    shipping_addr   varchar(50),
    user_id         integer REFERENCES users(id)
);

CREATE TABLE daily_deal(
    deal_date        date NOT NULL PRIMARY KEY,
    product_id       integer REFERENCES products(id),
    new_price        float(20)
);



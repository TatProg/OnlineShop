drop table if exists product_attribute,attribute,order_product,product,person,orders;
create table attribute (id serial primary key,
                        name varchar(50) not null,
                        description varchar(250));
create table product (id serial primary key,
                    name varchar(100) not null, 
                    price integer not null check (price>-1),
                    quantity integer not null check (price>-1),
                    description varchar(200),
                    isDeprecated integer default 0 check(isDeprecated>-1 and isDeprecated<2),
                    image bytea);
create table product_attribute (id serial primary key,
                                attribute_id integer references attribute(id), 
                                product_id integer references product(id));
create table person (id serial primary key, 
                    username varchar(50) not null, 
                    password varchar(250) not null,
                    name varchar(20) not null,
                    secondname varchar(50),
                    isAdmin integer default 0 check(isAdmin>-1 and isAdmin<2));
create table orders (id serial primary key, 
                    person_id integer references person(id), 
                    order_date date,
                    card_number varchar(19) not null,
                    isFinished integer default 0 check(isFinished>-1 and isFinished<2));
create table order_product (id serial primary key, 
                            product_id integer references product(id),
                            order_id integer references orders(id));

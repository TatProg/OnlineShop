insert into attribute(name,description) values ('Серьги','Красивое описание серёжек'),('Ожерелья','Красивое описание ожерелий'),('Кольца','Красивое описание колец');
insert into product (name,price,quantity,description,isDeprecated) values ('Серьги 1',15000,1,'какое-то описание',0),('Серьги 2',18000,3,'какое-то описание',0),('Серьги 3',20000,1,'какое-то описание',0),('Ожерелье 1',25000,5,'какое-то описание',0);
insert into product_attribute (product_id,attribute_id) values (1,1),(2,1),(3,1),(4,2);
insert into person (username,password,name,secondname,isAdmin) values ('admin','$2a$10$j753ShKXvta9WRGYo0yfK.4C8n.2.lGTBd3I9jzCQkOpEgNwhj37W','adminTester','adminTester',1),('user','$2a$10$MKt76PTApgdCbfV/CTC68utcbutHFybL12WkQZCIf0Gmi/JpPhklW','userTester','userTester',0);
insert into orders (person_id,order_date,card_number,isFinished) values (1,'13.04.2020','4578456256439173',1),(1,'13.04.2020','4578456256439173',0);
insert into order_product (product_id,order_id) values (1,1),(2,1),(3,2),(4,2);

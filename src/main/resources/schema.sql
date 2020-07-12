--Ingredient
CREATE TABLE IF NOT EXISTS Ingredient(
    id VARCHAR(4) NOT NULL ,
    name VARCHAR(32) NOT NULL ,
    type VARCHAR(10) NOT NULL
);

--Taco
CREATE TABLE IF NOT EXISTS Taco(
    id IDENTITY ,
    name VARCHAR(50) NOT NULL ,
    createdAt TIMESTAMP NOT NULL
);

--Taco
CREATE TABLE IF NOT EXISTS Taco_Ingredients(
   Taco BIGINT NOT NULL,
   Ingredient varchar(4) NOT NULL
);

ALTER TABLE Taco_Ingredients
    ADD FOREIGN  KEY (Taco) references Taco(id);
ALTER TABLE Taco_Ingredients
    ADD FOREIGN KEY (Ingredient) references Ingredient(id);

CREATE TABLE if not exists Taco_Order (
	id identity,
	deliveryName varchar(50) not null,
	deliveryStreet varchar(50) not null,
	deliveryCity varchar(50) not null,
	deliveryState varchar(50) not null,
	deliveryZip varchar(16) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists Taco_Order_Tacos (
	tacoOrder bigint not null,
	taco bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (taco) references Taco(id);
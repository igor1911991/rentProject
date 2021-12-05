DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS auto;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS drivers_license;




CREATE TABLE auto
(
	vin char(17),
	model varchar(50),
	body varchar(20),
	transmission varchar(10),
	price integer not null,
	booked boolean not null,
	PRIMARY KEY(vin)
);

CREATE TABLE drivers_license
(
	drivers_license_id char(17) not null,
	category varchar(3),
	driver_given_name varchar(20),
	driver_surname varchar(25),
	date_of_birth date not null,
	expires boolean not null,
	PRIMARY KEY(drivers_license_id)
);

CREATE TABLE client
(
	client_id SERIAL,
	given_name varchar(20),
	phone_number integer not null,
	date_of_birth date not null,
	drivers_license char(17) not null,
	PRIMARY KEY(client_id),
	FOREIGN KEY(drivers_license) REFERENCES drivers_license(drivers_license_id) ON DELETE RESTRICT
);

CREATE TABLE booking
(
	rental_order_id SERIAL,
	rental_order_status int not null,
        rental_order_date timestamp not null,
	client integer not null,
	auto char(17),
	rental_time integer not null,
	PRIMARY KEY(rental_order_id),
	FOREIGN KEY(client) REFERENCES client(client_id) ON DELETE RESTRICT,
	FOREIGN KEY(auto) REFERENCES auto(vin) ON DELETE RESTRICT
)
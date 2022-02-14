drop table if exists couple;
drop table if exists vendor_regist;
drop table if exists users;
drop table if exists wedding;
drop table if exists vendors;


create table if not exists vendors(
	vendor_id varchar primary key,
	vendor_name varchar(40),
	vendor_type varchar(20),
	vendor_cost double, --double isnt best choice for accuracy
	vendor_availability_begin date,
	vendor_availability_end date
);

create table if not exists wedding(
	wedding_id varchar primary key,
	wedding_date date UNIQUE NOT NULL,
	wedding_head_count int,
    wedding_couple varchar,
	wedding_budget double, --double isnt best choice for accuracy
	foreign key(wedding_venue) references vendors(vendor_id)
);

create table if not exists users(
	user_id varchar primary key,
	user_fname varchar(25),
	user_lname varchar(25),
	user_email varchar(25) UNIQUE,
	user_phone_number int (10),
	user_username varchar(25) UNIQUE,
	user_password varchar(25),
	user_meal_choice int,
	user_plus_one,
	user_is_attending boolean,
    user_type int, --0 for guest, 1 for couple, 2 for staff
	wedding_id varchar,
	foreign key(wedding_id) references wedding(wedding_id)
);

create table if not exists vendor_regist (
	vendor_reg_id varchar primary key,
	wedding_id varchar,
	vendor_id varchar,
	foreign key(wedding_id) references wedding(wedding_id),
	foreign key(vendor_id) references vendors(vendor_id)
);


create table if not exists couple(
    couple_id varchar primary key,
    partner_1_id varchar,
    partner_2_id varchar,
    foreign key(partner_1_id) references users (user_id),
    foreign key(partner_2_id) references users (user_id)
);


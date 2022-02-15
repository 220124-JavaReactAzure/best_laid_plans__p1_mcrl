drop table if exists couple;
drop table if exists vendor_regist;
drop table if exists users;
drop table if exists wedding;
drop table if exists vendors;


create table vendors(
	vendor_id varchar(250) primary key,
	vendor_name varchar(40),
	vendor_type varchar(20),
	vendor_cost double,
	vendor_availability_begin date,
	vendor_availability_end date
);

create table wedding(
	wedding_id varchar(250) primary key,
	wedding_date date UNIQUE NOT NULL,
	wedding_head_count int,
    wedding_couple varchar(250),
	wedding_budget double,
	foreign key(wedding_venue) references vendors(vendor_id)
);

create table users(
	user_id varchar(250) primary key,
	user_fname varchar(25),
	user_lname varchar(25),
	user_email varchar(25) UNIQUE,
	user_phone_number int (10),
	user_username varchar(25) UNIQUE,
	user_password varchar(25),
	user_meal_choice int,
	user_plus_one boolean,
	user_is_attending boolean,
    user_type int, --0 for guest, 1 for couple, 2 for staff
	wedding_id varchar(250),
	foreign key(wedding_id) references wedding(wedding_id)
);

create table vendor_regist (
	vendor_reg_id varchar(250) primary key,
	wedding_id varchar(250),
	vendor_id varchar(250),
	foreign key(wedding_id) references wedding(wedding_id),
	foreign key(vendor_id) references vendors(vendor_id)
);


create table couple(
    couple_id varchar(250) primary key,
    partner_1_id varchar(250),
    partner_2_id varchar(250),
    foreign key(partner_1_id) references users (user_id),
    foreign key(partner_2_id) references users (user_id)
);


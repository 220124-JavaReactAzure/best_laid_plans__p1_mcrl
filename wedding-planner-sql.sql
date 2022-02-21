drop table if exists couple;
drop table if exists vendor_regist;
drop table if exists users;
drop table if exists wedding;
drop table if exists vendors;


create table vendor_types(
	vendor_type_id int,
	vendor_type varchar(20)
);

create table meal_types(
	meal_type_id int,
	meal_type varchar(20)
);

create table vendors(
	vendor_id varchar(250) primary key,
	vendor_name varchar(40),
	vendor_type_id int,
	vendor_cost double precision,
	vendor_availability_begin date,
	vendor_availability_end date
	foreign key(vendor_type_id) references vendors_types(vendor_type_id)
);

create table wedding(
	wedding_id varchar(250) primary key,
	wedding_date date UNIQUE NOT NULL,
	wedding_head_count int,
	wedding_budget double precision,
	wedding_venue varchar (250),
	wedding_florist varchar (250),
	wedding_caterer varchar (250),
	wedding_musician varchar (250),
	wedding_photographer varchar (250),
	foreign key(wedding_venue) references vendors(vendor_id)
);

create table users(
	user_id varchar(250) primary key,
	user_name varchar(25),
	user_email varchar(25) UNIQUE,
	user_username varchar(25) UNIQUE,
	user_password varchar(25),
	user_meal_choice int,
	user_plus_one BIT,
	user_is_attending BIT,
    user_type int, --0 for guest, 1 for couple, 2 for staff
	wedding_id varchar(250),
	foreign key(wedding_id) references wedding(wedding_id)
	foreign key(user_meal_choice) references meal_types(meal_type_id)
);




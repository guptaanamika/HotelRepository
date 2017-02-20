-- create database hotel_repository;

-- use hotel_repository;

--Customer Registration
create table hotel_info (
id varchar(150) not null,
name varchar(50) not null,
version int(2) not null,
address varchar(100) not null,
contact varchar(15),
star_ranking int(1),
number_of_rooms int(3) not null,
created_on datetime(3) not null,
created_by varchar(50) not null,
updated_on datetime(3) not null,
updated_by varchar(50) not null,
constraint pk_id primary key (id),
);

create table hotel_facility_info (
id varchar(150) not null,
facility_type varchar(50) not null,
facility_desc varchar(150) not null,
created_on datetime(3) not null,
created_by varchar(50) not null,
updated_on datetime(3) not null,
updated_by varchar(50) not null,
constraint pk_id_facility primary key (id),
--CONSTRAINT FK_Id FOREIGN KEY (id) REFERENCES hotel_info(id)
);
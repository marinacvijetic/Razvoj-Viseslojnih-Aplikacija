insert into obrazovanje
values(nextval('obrazovanje_seq'), 'Diplomirani menadzer', '62', 'Menadzer');
insert into obrazovanje
values(nextval('obrazovanje_seq'), 'Diplomirani ekonomista', '62', 'Racunovodja');
insert into obrazovanje 
values(nextval('obrazovanje_seq'), 'Doktor nauka masinskog inzenjeringa', '80', 'Doktor');
insert into obrazovanje
values(nextval('obrazovanje_seq'), 'Inzenjer informacionih tehnologija', '62', 'Inzenjer');

insert into preduzece
values(nextval('preduzece_seq'), 'Umetnicka livnica - Stanisic', '12354', 'Novi Sad', '/');
insert into preduzece
values(nextval('preduzece_seq'), 'Continental d.o.o', '587746', 'Novi Sad');
insert into preduzece
values(nextval('preduzece_seq'), 'Hutchinson d.o.o', '21479', 'Ruma');

insert into sektor
values(nextval('sektor_seq'), 'Logistika', 'LG', 1);
insert into sektor
values(nextval('sektor_seq'), 'Proizvodnja', 'PP', 2);
insert into sektor
values(nextval('sektor_seq'), 'Racunovodstvo', 'RC', 2);
insert into sektor
values(nextval('sektor_seq'), 'Uprava', 'UP', 3);
insert into sektor
values (nextval('sektor_seq'), 'Tehnicka podrska', 'IT', 1);

insert into radnik
values(nextval('radnik_seq'), 'Marina', 'Cvijetic', 107989, 2, 3);
insert into radnik 
values(nextval('radnik_seq'), 'Valentina', 'Andric', 58746, 1, 1);
insert into radnik 
values(nextval('radnik_seq'), 'Una', 'Obradovic', 24784, 3, 4);
insert into radnik
values (nextval('radnik_seq'), 'Ognjen', 'Dupljanin', 879247, 1, 3);
insert into radnik
values (nextval('radnik_seq'), 'Pavle', 'Stanisic', 21478, 1, 4);
insert into radnik
values (nextval('radnik_seq'), 'Mladen', 'Draganovic', 98745, 4, 5);
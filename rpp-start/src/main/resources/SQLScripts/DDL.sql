drop table if exists preduzece cascade;
drop table if exists sektor cascade;
drop table if exists obrazovanje cascade;
drop table if exists radnik cascade;

DROP SEQUENCE IF EXISTS preduzece_seq;
DROP SEQUENCE IF EXISTS obrazovanje_seq;
DROP SEQUENCE IF EXISTS sektor_seq;
DROP SEQUENCE IF EXISTS radnik_seq;

create table preduzece (
id_preduzece integer,
naziv varchar(100),
pib integer,
sediste varchar(100),
opis varchar(500)
);

create table obrazovanje (
id_obrazovanje integer,
naziv varchar(50),
stepen_strucne_spreme varchar(10),
opis varchar(500)
);

create table sektor(
id_sektor integer,
naziv varchar(100),
oznaka varchar(10),
preduzece integer not null
);

create table radnik (
id_radnik integer,
ime varchar(50),
prezime varchar (50),
broj_lk integer,
obrazovanje integer not null,
sektor integer not null
 );
 
 ALTER TABLE preduzece
	ADD CONSTRAINT pk_preduzece PRIMARY KEY (id_preduzece);
ALTER TABLE obrazovanje 
	ADD CONSTRAINT pk_obrazovanje PRIMARY KEY (id_obrazovanje);
ALTER TABLE sektor 
	ADD CONSTRAINT pk_sektor PRIMARY KEY (id_sektor);
ALTER TABLE radnik 
	ADD CONSTRAINT pk_radnik PRIMARY KEY (id_radnik);
   
ALTER TABLE sektor 
	ADD CONSTRAINT fk_sektor_preduzece FOREIGN KEY (preduzece)
    REFERENCES preduzece(id_preduzece);
   
ALTER TABLE radnik
	ADD CONSTRAINT fk_radnik_obrazovanje FOREIGN KEY (obrazovanje)
    REFERENCES obrazovanje(id_obrazovanje);
    
ALTER TABLE radnik
	ADD CONSTRAINT fk_radnik_sektor FOREIGN KEY (sektor)
    REFERENCES sektor(id_sektor);    
    
CREATE INDEX idx_pk_preduzece ON preduzece(id_preduzece);  
CREATE INDEX idx_pk_obrazovanje ON obrazovanje(id_obrazovanje);
CREATE INDEX idx_pk_sektor ON sektor(id_sektor);
CREATE INDEX idx_pk_radnik ON radnik(id_radnik);

CREATE INDEX idx_fk_sektor_preduzece ON sektor(preduzece);
CREATE INDEX idx_fk_radnik_obrazovanje ON radnik(obrazovanje); 	
CREATE INDEX idx_fk_radnik_sektor ON radnik(sektor);    
    
CREATE SEQUENCE IF NOT EXISTS preduzece_seq INCREMENT 1 START 1;  
CREATE SEQUENCE IF NOT EXISTS obrazovanje_seq INCREMENT 1 START 1;   
CREATE SEQUENCE IF NOT EXISTS sektor_seq INCREMENT 1 START 1;   
CREATE SEQUENCE IF NOT EXISTS radnik_seq INCREMENT 1 START 1;   

ALTER TABLE preduzece ALTER COLUMN id_preduzece SET DEFAULT nextval('preduzece_seq');
ALTER TABLE obrazovanje ALTER COLUMN id_obrazovanje SET DEFAULT nextval('obrazovanje_seq');
ALTER TABLE sektor ALTER COLUMN id_sektor SET DEFAULT nextval('sektor_seq');
ALTER TABLE radnik ALTER COLUMN id_radnik SET DEFAULT nextval('radnik_seq');
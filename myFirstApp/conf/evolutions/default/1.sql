# --- !Ups
CREATE TABLE "traineeuser" ("username" VARCHAR(200),"userpassword" VARCHAR(100),
"userrole" boolean,"userid" serial PRIMARY KEY );

CREATE TABLE "awards" ("sno" INT,"name" VARCHAR(200),"details" VARCHAR(200),"year" INT);

INSERT INTO "traineeuser" VALUES('test','123',false,1);

INSERT INTO "awards" VALUES(1,'akshay','Lan game First Prize',2016);
INSERT INTO "awards" VALUES(2,'deepak','Coding First Prize',2016);




# --- !Downs
DROP TABLE "traineeuser";

DROP TABLE "awards";

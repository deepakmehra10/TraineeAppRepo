# --- !Ups
CREATE TABLE "traineeuser" ("username" VARCHAR(200),"userpassword" VARCHAR(100),"userrole" boolean,"userid" serial PRIMARY KEY );
CREATE TABLE "awards" ("name" VARCHAR(200),"details" VARCHAR(200),"year" INT,"sno" serial PRIMARY KEY,);
CREATE TABLE "language" ("sno" INT,"studname" VARCHAR(200),"langname" VARCHAR(200),"fluency" VARCHAR(200));
CREATE TABLE "assignment" ("sno" serial PRIMARY KEY ,"studname" VARCHAR(200),"name" VARCHAR(200),"date" VARCHAR(50),"marks" INT,"remarks" VARCHAR(200));
CREATE TABLE "programming_language" ("sno" INT,"studname" VARCHAR(200),"langname" VARCHAR(200),"fluency" VARCHAR(200));
CREATE TABLE "intern"("name" VARCHAR(200), "email" VARCHAR(200),"mobile" INT,"award" VARCHAR(200));



INSERT INTO "traineeuser" VALUES('test','123',false,1);

INSERT INTO "awards" VALUES('akshay','Lan game First Prize',2016,1);
INSERT INTO "awards" VALUES('deepak','Coding First Prize',2016,2);

INSERT INTO "language" VALUES(1,'akshay','hindi','read/write');
INSERT INTO "language" VALUES(2,'deepak','english','read/write');

INSERT INTO "assignment" VALUES(1,'akshay','scala','1st jan',6,'average');
INSERT INTO "assignment" VALUES(2,'deepak','scala','3rd jan',6,'average');

INSERT INTO "programming_language" VALUES(1,'akshay','scala','read/write');
INSERT INTO "programming_language" VALUES(2,'deepak','scala','read/write');

INSERT INTO "intern" VALUES('sangeeta','sang@gmail.com',4342534,'Java Certified');
INSERT INTO "intern" VALUES('admin','admin@gmail.com',2432466,'C# Certified');

# --- !Downs
DROP TABLE "traineeuser";

DROP TABLE "awards";

DROP TABLE "language";

DROP TABLE "assignment";

DROP TABLE "programming_language";

DROP TABLE "intern";
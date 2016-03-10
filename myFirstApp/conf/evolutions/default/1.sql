# --- !Ups
CREATE TABLE "traineeuser" ("username" VARCHAR(200),"userpassword" VARCHAR(100),
"userrole" boolean,"userid" serial PRIMARY KEY );

INSERT INTO "traineeuser" VALUES('test','123',false,1);


# --- !Downs
DROP TABLE "traineeuser";
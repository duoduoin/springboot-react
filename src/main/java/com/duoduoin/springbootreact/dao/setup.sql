alter session set "_ORACLE_SCRIPT"=true; 

create user demo_1 identified by xxxxxx;

GRANT CREATE SESSION, CREATE TABLE, CREATE VIEW, CREATE ANY INDEX, CREATE SEQUENCE, CREATE TYPE, CREATE PROCEDURE, EXECUTE ANY PROCEDURE, EXECUTE ANY TYPE 
TO demo_1 
WITH ADMIN OPTION; 


create or replace TYPE "KEYVALUEOBJECT" AS OBJECT (
    name    VARCHAR(255),
    value   VARCHAR(255)
);

create or replace TYPE "MY_OBJECT_ARRAY" AS
    TABLE OF KEYVALUEOBJECT;


create or replace PROCEDURE sample_proc (
    i_array   IN my_object_array,
    o_array   OUT my_object_array
) AS
BEGIN
    o_array := my_object_array ();
    FOR i IN 1..i_array.count LOOP
        o_array.extend;
        o_array(i) := keyvalueobject(i_array(i).name,i_array(i).value);

    END LOOP;

END;
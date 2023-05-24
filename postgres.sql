create user marco with password '1234';

create database ventedb with template=template0 owner=marco;
\c ventedb;
alter default privileges grant all on tables to marco;
alter default privileges grant all on sequences to marco;


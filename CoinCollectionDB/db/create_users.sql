create user dbu
identified by passme
default tablespace users;
grant connect, create any view, resource
to dbu;
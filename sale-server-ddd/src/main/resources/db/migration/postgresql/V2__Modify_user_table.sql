DROP SEQUENCE IF EXISTS sale.users_id_seq CASCADE;

ALTER TABLE sale.USERS
    ALTER COLUMN ID TYPE VARCHAR(50);

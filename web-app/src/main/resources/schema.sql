CREATE TABLE FILES (id long, content BLOB);


# UUID_TO_BIN(uid)
CREATE TABLE USER(
    id BINARY(16) PRIMARY KEY NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    date_created DATETIME NOT NULL);
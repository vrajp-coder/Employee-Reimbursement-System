DROP TABLE users CASCADE;

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL,
	email VARCHAR(200) NOT NULL,
	username VARCHAR(200) NOT NULL,
	"password" VARCHAR(200) NOT NULL,
	"admin" BOOL
);

SELECT * FROM users


DROP TABLE reimbursements;

CREATE TABLE reimbursements (
	reimbursement_id SERIAL PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	amount INT NOT NULL,
	message VARCHAR(2000),
	user_id INT,
	complete VARCHAR(20),
	CONSTRAINT reimbursements_users_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

SELECT * FROM reimbursements
INSERT INTO PROFESSOR (first_name, last_name, email, phone)
VALUES ('Petros', 'Petrou', 'petros@example.com', '555555555'),
       ('Giorgos', 'Georgiou', 'georgoiu@example.com', '555555555');

INSERT INTO CLASSROOM (name, PROFESSOR_IN_CHARGE_ID)
VALUES ('Classroom 1', 1),
       ('Computer Science Lab', 2),
       ('Classroom 2', 1),
       ('Classroom 3', 1),
       ('Physics Lab', 2);

INSERT INTO STUDENT (first_name, last_name, grade, birth_date, classroom_id)
VALUES ('alex', 'Tsih', 7.25, '1995-01-05', 1),
       ('Lorenza', 'Hinken', 8.66, '1991-10-28', 3),
       ('Germaine', 'Grafhom', 8.29, '1999-03-21', 5),
       ('Anabal', 'Surfleet', 4.37, '1994-07-20', 2),
       ('Braden', 'Kittless', 7.4, '1992-10-26', 2),
       ('Renate', 'Reisk', 6.81, '1994-02-08', 3),
       ('Gregor', 'Spitaro', 6.87, '1994-07-01', 5),
       ('Huberto', 'Paris', 1.11, '1991-05-01', 4),
       ('Lesli', 'Anten', 5.93, '1991-10-27', 5),
       ('Darci', 'Van Oort', 6.51, '1995-12-14', 2),
       ('Xylia', 'Espinas', 8.27, '1995-08-17', 4),
       ('Hiram', 'Bisacre', 9.64, '1991-11-11', 5),
       ('Drucill', 'Fantham', 6.99, '1996-07-14', 2),
       ('Reinhold', 'Austwick', 2.29, '1998-06-11', 5),
       ('Jakie', 'Ruskin', 6.19, '1992-08-25', 2),
       ('Bren', 'Barchrameev', 7.34, '1998-08-06', 2),
       ('Casandra', 'MacAulay', 2.19, '1990-12-01', 1),
       ('Radcliffe', 'Giovani', 3.16, '1997-11-13', 3),
       ('Ddene', 'Karpushkin', 3.6, '1994-05-12', 1),
       ('Sidney', 'Thynne', 9.52, '1996-12-11', 4),
       ('Zorina', 'Philips', 6.36, '1990-12-03', 2),
       ('Wiley', 'Achurch', 5.23, '1999-06-18', 4),
       ('Eldridge', 'Dimitrie', 3.42, '1999-02-03', 4),
       ('Caresse', 'Dillway', 9.75, '1991-01-16', 2),
       ('Maurie', 'Dey', 3.04, '1998-11-01', 1),
       ('Felicio', 'Road', 4.6, '1993-06-22', 5);



INSERT INTO USER_DOMAIN (username, password, email, enabled, professor_id)
VALUES ('alex18gr', '$2a$12$tHBvau..Bo39lV2MaYSIXOeBBOYAwSs3GCR/Sx.E.oLymrUEty7oS', 'alex@mail.com', 1, null),
       ('user1', '$2a$12$tHBvau..Bo39lV2MaYSIXOeBBOYAwSs3GCR/Sx.E.oLymrUEty7oS', 'user1@mail.com', 1, 1);

INSERT INTO authority (user_id, authority)
VALUES (1, 'ADMIN'),
       (2, 'PROFESSOR');
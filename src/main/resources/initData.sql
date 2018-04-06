INSERT INTO departments (id, name) VALUES (1, 'Rich business owners');

INSERT INTO positions (id, hour_salary, name) VALUES (1, 5000, 'Business owner');

INSERT INTO statuses (id, type) VALUES (1, 'Working');
INSERT INTO statuses (id, type) VALUES (2, 'Sick leave');
INSERT INTO statuses (id, type) VALUES (3, 'Vacation');

-- Password 123 encoded: $2a$10$aCMbzEnmUgaROnH4uqQ5v.PYUyrZUneJVs1aoj22tBk7dd5Hk8FYa
INSERT INTO users (id, username, password, enabled) VALUES (1, 'roman', '$2a$10$aCMbzEnmUgaROnH4uqQ5v.PYUyrZUneJVs1aoj22tBk7dd5Hk8FYa', 1);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'kostya', '$2a$10$aCMbzEnmUgaROnH4uqQ5v.PYUyrZUneJVs1aoj22tBk7dd5Hk8FYa', 1);
INSERT INTO users (id, username, password, enabled) VALUES (3, 'nastya', '$2a$10$aCMbzEnmUgaROnH4uqQ5v.PYUyrZUneJVs1aoj22tBk7dd5Hk8FYa', 1);
INSERT INTO users (id, username, password, enabled) VALUES (4, 'user', '$2a$10$aCMbzEnmUgaROnH4uqQ5v.PYUyrZUneJVs1aoj22tBk7dd5Hk8FYa', 1);
INSERT INTO users (id, username, password, enabled) VALUES (5, 'moderator', '$2a$10$aCMbzEnmUgaROnH4uqQ5v.PYUyrZUneJVs1aoj22tBk7dd5Hk8FYa', 1);

INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2);

INSERT INTO employees (id, name, surname, email, REGISTERED_DATE, POSITION_ID, DEPARTMENT_ID, STATUS_ID, USER_ID)
VALUES (1, 'Roman', 'Yarosh', 'roman.yarosh@rich.com', now(), 1, 1, 1, 1);

INSERT INTO employees (id, name, surname, email, REGISTERED_DATE, POSITION_ID, DEPARTMENT_ID, STATUS_ID, USER_ID)
VALUES (2, 'Konstantin', 'Petrov', 'konstantin.petrov@rich.com', now(), 1, 1, 1, 2);

INSERT INTO employees (id, name, surname, email, REGISTERED_DATE, POSITION_ID, DEPARTMENT_ID, STATUS_ID, USER_ID)
VALUES (3, 'Nastya', 'Degtyarova', 'nastya.degtjarova@rich.com', now(), 1, 1, 1, 3);

INSERT INTO employees (id, name, surname, email, REGISTERED_DATE, POSITION_ID, DEPARTMENT_ID, STATUS_ID, USER_ID)
VALUES (4, 'user', 'user', 'user@rich.com', now(), 1, 1, 1, 4);

INSERT INTO employees (id, name, surname, email, REGISTERED_DATE, POSITION_ID, DEPARTMENT_ID, STATUS_ID, USER_ID)
VALUES (5, 'moderator', 'moderator', 'moderator@rich.com', now(), 1, 1, 1, 5);

INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (1,1,2018,1200000, 1);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (2,1,2018,1200000, 2);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (3,1,2018,1200000, 3);

INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (4,2,2018,1200000, 1);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (5,2,2018,1200000, 2);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (6,2,2018,1200000, 3);

INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (7,3,2018,1200000, 1);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (8,3,2018,1200000, 2);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (9,3,2018,1200000, 3);

INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (10,1,2018,1200000, 4);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (11,2,2018,1200000, 4);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (12,3,2018,1200000, 4);

INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (13,1,2018,1200000, 5);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (14,2,2018,1200000, 5);
INSERT INTO salaries (id, month, year, salary_sum, employee_id) VALUES (15,3,2018,1200000, 5);


INSERT INTO event_types(id, rate, type) VALUES (1, 1, 'Working day');
INSERT INTO event_types(id, rate, type) VALUES (2, 1, 'Technical training');

INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (1, now() - INTERVAL 3 DAY, 8,1,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (2, now() - INTERVAL 3 DAY, 8,2,2);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (3, now() - INTERVAL 3 DAY, 8,1,3);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (4, now() - INTERVAL 2 DAY, 8,2,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (5, now() - INTERVAL 2 DAY, 8,1,2);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (6, now() - INTERVAL 2 DAY, 8,2,3);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (7, now() - INTERVAL 1 DAY, 8,1,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (8, now() - INTERVAL 1 DAY, 8,2,2);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (9, now() - INTERVAL 1 DAY, 8,1,3);

INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (10, now() - INTERVAL 3 DAY, 8,1,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (11, now() - INTERVAL 2 DAY, 8,1,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (12, now() - INTERVAL 1 DAY, 8,1,1);

INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (13, now() - INTERVAL 3 DAY, 8,1,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (14, now() - INTERVAL 2 DAY, 8,1,1);
INSERT INTO events (id, event_date, hours, type_id, CREATED_USER_ID) VALUES (15, now() - INTERVAL 1 DAY, 8,1,1);


INSERT INTO employee_events (employee_id, event_id) VALUES (1, 1);
INSERT INTO employee_events (employee_id, event_id) VALUES (2, 2);
INSERT INTO employee_events (employee_id, event_id) VALUES (3, 3);
INSERT INTO employee_events (employee_id, event_id) VALUES (1, 4);
INSERT INTO employee_events (employee_id, event_id) VALUES (2, 5);
INSERT INTO employee_events (employee_id, event_id) VALUES (3, 6);
INSERT INTO employee_events (employee_id, event_id) VALUES (1, 7);
INSERT INTO employee_events (employee_id, event_id) VALUES (2, 8);
INSERT INTO employee_events (employee_id, event_id) VALUES (3, 9);

INSERT INTO employee_events (employee_id, event_id) VALUES (4, 10);
INSERT INTO employee_events (employee_id, event_id) VALUES (4, 11);
INSERT INTO employee_events (employee_id, event_id) VALUES (4, 12);

INSERT INTO employee_events (employee_id, event_id) VALUES (5, 13);
INSERT INTO employee_events (employee_id, event_id) VALUES (5, 14);
INSERT INTO employee_events (employee_id, event_id) VALUES (5, 15);
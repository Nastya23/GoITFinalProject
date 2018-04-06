-- -----------------------------------------------------
-- Drop database if exist
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS goit_final_project;

-- -----------------------------------------------------
-- Create database if not exists: goit_final_project;
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS goit_final_project
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

-- -----------------------------------------------------
-- Use database: goit_final_project
-- -----------------------------------------------------
USE goit_final_project;

-- -----------------------------------------------------
-- Table: roles
-- stores information about user roles.
-- -----------------------------------------------------
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  ID   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(255)     NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  UNIQUE KEY UNI_NAME (NAME)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: users
-- stores information about users.
-- -----------------------------------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  ID       INT(10)      NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  ENABLED TINYINT(4) DEFAULT '1',

  PRIMARY KEY (ID) USING BTREE,
  UNIQUE KEY UNI_USERNAME (USERNAME)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: departments
-- stores information about departments.
-- -----------------------------------------------------
DROP TABLE IF EXISTS departments;
CREATE TABLE departments (
  ID   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(128)     NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  UNIQUE KEY UNI_NAME (NAME)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: positions
-- stores information about positions.
-- -----------------------------------------------------
DROP TABLE IF EXISTS positions;
CREATE TABLE positions (
  ID          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NAME        VARCHAR(128) NOT NULL,
  HOUR_SALARY DECIMAL NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  UNIQUE KEY UNI_NAME (NAME)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: event_types
-- stores information about event types.
-- -----------------------------------------------------
DROP TABLE IF EXISTS event_types;
CREATE TABLE event_types (
  ID   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  TYPE VARCHAR(128)     NOT NULL,
  RATE DECIMAL          NOT NULL,

  PRIMARY KEY (ID) USING BTREE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: events
-- stores information about events.
-- -----------------------------------------------------
DROP TABLE IF EXISTS events;
CREATE TABLE events (
  ID              INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  EVENT_DATE      DATETIME         NOT NULL,
  HOURS           INT(10)          NOT NULL,
  TYPE_ID         INT(10)          NOT NULL,
  CREATED_USER_ID INT(10)          NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  FOREIGN KEY (TYPE_ID) REFERENCES event_types (ID),
  FOREIGN KEY (CREATED_USER_ID) REFERENCES users (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: statuses
-- stores information about statuses.
-- -----------------------------------------------------
DROP TABLE IF EXISTS statuses;
CREATE TABLE statuses (
  ID   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  TYPE VARCHAR(10)      NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  UNIQUE KEY UNI_TYPE (TYPE)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: employees
-- stores information about employees.
-- -----------------------------------------------------
DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
  ID              INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NAME            VARCHAR(20)      NOT NULL,
  SURNAME         VARCHAR(20)      NOT NULL,
  MIDDLE_NAME     VARCHAR(20),
  EMAIL           VARCHAR(50)      NOT NULL,
  REGISTERED_DATE DATETIME         NOT NULL,
  POSITION_ID     INT(10)          NOT NULL,
  DEPARTMENT_ID   INT(10)          NOT NULL,
  STATUS_ID       INT(10)          NOT NULL,
  USER_ID         INT(10)          NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  FOREIGN KEY (POSITION_ID) REFERENCES positions (POSITION_ID),
  FOREIGN KEY (DEPARTMENT_ID) REFERENCES departments (DEPARTMENT_ID),
  FOREIGN KEY (STATUS_ID) REFERENCES statuses (STATUS_ID),
  FOREIGN KEY (USER_ID) REFERENCES users (USER_ID),
  UNIQUE KEY UNI_EMAIL (EMAIL)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: employee_events
-- table for mapping employee and events.
-- -----------------------------------------------------

DROP TABLE IF EXISTS employee_events;
CREATE TABLE employee_events (
  EMPLOYEE_ID INT(10) UNSIGNED NOT NULL,
  EVENT_ID    INT(10) UNSIGNED NOT NULL,

  FOREIGN KEY (EMPLOYEE_ID) REFERENCES employees (EMPLOYEE_ID),
  FOREIGN KEY (EVENT_ID) REFERENCES events (EVENT_ID),

  UNIQUE (EVENT_ID, EMPLOYEE_ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: user_roles
-- table for mapping user and role.
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
  USER_ID INT(10) UNSIGNED NOT NULL,
  ROLE_ID INT(10) UNSIGNED NOT NULL,

  FOREIGN KEY (USER_ID) REFERENCES users (USER_ID),
  FOREIGN KEY (ROLE_ID) REFERENCES roles (ROLE_ID),

  UNIQUE (USER_ID, ROLE_ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table: salaries
-- table for employee salaries for each month
-- -----------------------------------------------------
DROP TABLE IF EXISTS salaries;
CREATE TABLE salaries (
  ID          INT(10) UNSIGNED NOT NULL,
  MONTH       INT              NOT NULL,
  YAER        INT              NOT NULL,
  SALARY_SUM  DECIMAL          NOT NULL,
  EMPLOYEE_ID INT(10) UNSIGNED NOT NULL,

  PRIMARY KEY (ID) USING BTREE,
  FOREIGN KEY (EMPLOYEE_ID) REFERENCES employees (EMPLOYEE_ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

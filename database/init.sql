CREATE DATABASE IF NOT EXISTS bugtracker;
USE bugtracker;

CREATE TABLE IF NOT EXISTS Users (
    userId INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    firstName VARCHAR(64) NOT NULL,
    lastName VARCHAR(64) NOT NULL,
    PRIMARY KEY (userId)
);

CREATE TABLE IF NOT EXISTS Status (
    statusId INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    statusName VARCHAR(64) NOT NULL,
    color INT UNSIGNED  NOT NULL, 
    PRIMARY KEY (statusId)
);

CREATE TABLE IF NOT EXISTS Labels (
    labelId INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    labelName VARCHAR(64) NOT NULL,
    PRIMARY KEY (labelId)
);

CREATE TABLE IF NOT EXISTS Tickets (
    ticketId INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    creatorId INT UNSIGNED  NOT NULL,
    assigneeId INT UNSIGNED  NOT NULL,
    statusId INT UNSIGNED  NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    creationTimestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ticketId) -- ,
    -- FOREIGN KEY (creatorId) REFERENCES Users (userId),
    -- FOREIGN KEY (assigneeId) REFERENCES Users (userId),
    -- FOREIGN KEY (statusId) REFERENCES Status (statusId)
);

CREATE TABLE IF NOT EXISTS TicketComments (
    commentId INT UNSIGNED  NOT NULL,
    ticketId INT UNSIGNED  NOT NULL,
    creatorId INT UNSIGNED  NOT NULL,
    content TEXT NOT NULL,
    creationTimestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (commentId, ticketId),
    FOREIGN KEY (ticketId) REFERENCES Tickets (ticketId),
    FOREIGN KEY (creatorId) REFERENCES Users (userId)
);

CREATE TABLE IF NOT EXISTS Workflow (
    statusId INT UNSIGNED  NOT NULL,
    nextStatusId INT UNSIGNED  NOT NULL,
    PRIMARY KEY (statusId, nextStatusId),
    FOREIGN KEY (statusId) REFERENCES Status (statusId),
    FOREIGN KEY (nextStatusId) REFERENCES Status (statusId)
);

CREATE TABLE IF NOT EXISTS TicketToLabels (
    ticketId INT UNSIGNED  NOT NULL,
    labelId INT UNSIGNED  NOT NULL,
    PRIMARY KEY (ticketId, labelId),
    FOREIGN KEY (ticketId) REFERENCES Tickets (ticketId),
    FOREIGN KEY (labelId) REFERENCES Labels (labelId)
);

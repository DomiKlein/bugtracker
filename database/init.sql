CREATE DATABASE IF NOT EXISTS bugtracker;
USE bugtracker;

CREATE TABLE IF NOT EXISTS Users (
    userId INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    firstName VARCHAR(64) NOT NULL,
    lastName VARCHAR(64) NOT NULL,
    PRIMARY KEY (userId)
);

CREATE TABLE IF NOT EXISTS Status (
    statusId INT NOT NULL AUTO_INCREMENT,
    statusName VARCHAR(64) NOT NULL,
    color CHAR(6) NOT NULL, 
    PRIMARY KEY (statusId)
);

CREATE TABLE IF NOT EXISTS Labels (
    labelId INT NOT NULL AUTO_INCREMENT,
    labelName VARCHAR(64) NOT NULL,
    PRIMARY KEY (labelId)
);

CREATE TABLE IF NOT EXISTS Tickets (
    ticketId INT NOT NULL AUTO_INCREMENT,
    creatorId INT NOT NULL,
    assigneeId INT NOT NULL,
    statusId INT NOT NULL,
    title VARCHAR(255),
    creationTimestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ticketId),
    FOREIGN KEY (creatorId) REFERENCES Users (userId),
    FOREIGN KEY (assigneeId) REFERENCES Users (userId),
    FOREIGN KEY (statusId) REFERENCES Status (statusId)
);

CREATE TABLE IF NOT EXISTS TicketComments (
    commentId INT NOT NULL,
    ticketId INT NOT NULL,
    creatorId INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    creationTimestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (commentId, ticketId),
    FOREIGN KEY (ticketId) REFERENCES Tickets (ticketId),
    FOREIGN KEY (creatorId) REFERENCES Users (userId)
);

CREATE TABLE IF NOT EXISTS Workflow (
    statusId INT NOT NULL,
    nextStatusId INT NOT NULL,
    PRIMARY KEY (statusId, nextStatusId),
    FOREIGN KEY (statusId) REFERENCES Status (statusId),
    FOREIGN KEY (nextStatusId) REFERENCES Status (statusId)
);

CREATE TABLE IF NOT EXISTS TicketToLabels (
    ticketId INT NOT NULL,
    labelId INT NOT NULL,
    PRIMARY KEY (ticketId, labelId),
    FOREIGN KEY (ticketId) REFERENCES Tickets (ticketId),
    FOREIGN KEY (labelId) REFERENCES Labels (labelId)
);
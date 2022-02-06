/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-02-05 22:21:44.

export interface Ticket extends DatabaseEntity {
    ticketId?: number;
    creator: User;
    assignee?: User;
    title: string;
    description: string;
    creationTimestamp?: Date;
}

export interface TicketComment extends DatabaseEntity {
    commentId?: number;
    creator: User;
    ticket: Ticket;
    comment: string;
    creationTimestamp?: Date;
}

export interface User extends DatabaseEntity {
    userId?: number;
    username: string;
    firstName: string;
    lastName: string;
}

export interface DatabaseEntity {
    id?: any;
    detached?: boolean;
}

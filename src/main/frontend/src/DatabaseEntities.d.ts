/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-02-06 02:35:52.

export interface Ticket {
    ticketId?: number;
    creator: User;
    assignee?: User;
    title: string;
    description: string;
    creationTimestamp?: Date;
}

export interface TicketComment {
    commentId?: number;
    creator: User;
    ticket: Ticket;
    comment: string;
    creationTimestamp?: Date;
}

export interface User {
    userId?: number;
    username: string;
    firstName: string;
    lastName: string;
}

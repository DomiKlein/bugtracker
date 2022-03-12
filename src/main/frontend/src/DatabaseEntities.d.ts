/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-03-12 21:20:32.

export interface AuthenticationResponse {
    user: User;
    authenticationToken: string;
    refreshToken: string;
}

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
    role: string;
    password: string;
}

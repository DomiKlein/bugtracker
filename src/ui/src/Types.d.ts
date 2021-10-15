/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2021-10-15 17:31:34.

export interface User extends DatabaseEntity {
    userId: number;
    username?: string;
    firstName?: string;
    lastName?: string;
}

export interface DatabaseEntity {
    id?: any;
    detached?: boolean;
}

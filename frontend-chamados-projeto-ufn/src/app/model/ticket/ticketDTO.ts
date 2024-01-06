export interface TicketDTO {
    id?: string;
    status?: string;
    priority?: string;
    createdAt?: Date;
    closedAt?: Date;
    commentId?: string;
    topicId?: string;
    userId?: string;
}
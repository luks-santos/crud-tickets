export interface TicketDTO {
    id: string | null;
    status: string;
    priority: string;
    createdAt: Date | null;
    closedAt: Date | null;
    commentId: string;
    topicId: string;
    userId: string | null;
}
import { Topic } from "../topic/topic";

export interface Ticket {
   id: string;
   status: string;
   priority: string;
   createdAt: Date;
   closedAt: Date;
   username: string;
   personName: string;
   comment: Comment;
   topic: Topic;
}
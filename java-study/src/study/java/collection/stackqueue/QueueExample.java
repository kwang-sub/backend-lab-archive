package study.java.collection.stackqueue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<Message> messageQueue = new LinkedList<>();

        messageQueue.offer(new Message("1", "1"));
        messageQueue.offer(new Message("1", "2"));
        messageQueue.offer(new Message("1", "3"));
        messageQueue.offer(new Message("1", "4"));

        while (!messageQueue.isEmpty()){
            Message m = messageQueue.poll();
            System.out.println(m.command + " " + m.to);
        }
    }
}

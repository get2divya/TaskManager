package iptiq;

import java.util.PriorityQueue;

public class TaskManager1 implements TaskManagerInterface {

    private static int capacity;
    private static PriorityQueue<Process> pq;


    public TaskManager1(int capacity) {
        this.capacity = capacity;
        this.pq = new PriorityQueue<Process>(capacity);
    }

    public PriorityQueue<Process> getPq() {
        return pq;
    }

    public int getCapacity() {
        return capacity;
    }

    public PriorityQueue<Process> add(Process p) throws Exception {
        if (this.pq.size() < capacity) {
            pq.add(p);
        } else {
            throw new Exception("Queue is full upto maximum capacity");
        }
        return pq;
    }
}

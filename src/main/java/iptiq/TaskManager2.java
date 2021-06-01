package iptiq;

import java.util.PriorityQueue;

public class TaskManager2 implements TaskManagerInterface {

    private static int capacity;
    private static PriorityQueue<Process> sortByTimestampPQ;

    public TaskManager2(int capacity) {
        this.capacity = capacity;
        this.sortByTimestampPQ = new PriorityQueue<>(capacity, new FIFOComparator());
    }

    public int getCapacity() {
        return capacity;
    }

    public PriorityQueue<Process> getSortByTimestampPQ() {
        return sortByTimestampPQ;
    }

    public PriorityQueue<Process> add(Process p) {
        if (this.sortByTimestampPQ.size() >= capacity) {
            Process tobeRemovedProcess = sortByTimestampPQ.peek(); // remove FIFO process
            tobeRemovedProcess.kill();
            TaskManagerInterface.kill(tobeRemovedProcess, sortByTimestampPQ);
        }
        sortByTimestampPQ.add(p);
        return sortByTimestampPQ;
    }
}

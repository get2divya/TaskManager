package iptiq;

import java.util.PriorityQueue;

public class TaskManager3 implements TaskManagerInterface {

    private static int capacity;
    private static PriorityQueue<Process> sortByPriorityPQ;

    public TaskManager3(int capacity) {
        this.capacity = capacity;
        this.sortByPriorityPQ = new PriorityQueue<>(capacity, new PriorityComparator());
    }

    public int getCapacity() {
        return capacity;
    }

    public PriorityQueue<Process> getSortByPriorityPQ() {
        return sortByPriorityPQ;
    }

    public PriorityQueue<Process> add(Process inputProcess) {
        if (this.sortByPriorityPQ.size() >= capacity) {
            Process tobeRemovedProcess = sortByPriorityPQ.peek(); // remove lowest priority,oldest
            tobeRemovedProcess.kill();
            TaskManagerInterface.kill(tobeRemovedProcess, sortByPriorityPQ);
        }
        sortByPriorityPQ.add(inputProcess);
        return sortByPriorityPQ;
    }

}

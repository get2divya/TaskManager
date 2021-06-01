package iptiq;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Process> {
    // Overriding compare()method of Comparator
    // we remove the lowest priority first
    public int compare(Process p1, Process p2) {
        if (p1.getPriority().value < p2.getPriority().value)
            return -1;
        else if (p1.getPriority().value > p2.getPriority().value)
            return 1;
        return 0;
    }
}
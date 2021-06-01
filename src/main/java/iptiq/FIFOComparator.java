package iptiq;

import java.util.Comparator;

public class FIFOComparator implements Comparator<Process> {
    // Overriding compare()method of Comparator, compares using timestamp, FIFO
    public int compare(Process p1, Process p2) {
        return p1.getTimestamp().compareTo(p2.getTimestamp());
    }
}

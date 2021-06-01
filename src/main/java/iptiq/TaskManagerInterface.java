package iptiq;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static iptiq.Process.RUNNING;

public interface TaskManagerInterface {

    /**
     * @param p The process to be added to OS queue
     * @return Queue of all running Processes
     * @throws Exception throw exception if process could not be added as Queue is full
     */
    public PriorityQueue<Process> add(Process p) throws Exception;

    /**
     * @param pq Priority Queue running process need to be listed
     */
    public static void list(PriorityQueue<Process> pq) {
       for (Process p: pq) {
            if (p.getStatus() == RUNNING) {
                System.out.print(p + "\n");
            }
        }
    }

    /**
     *
     * @param pq Priority Queue running process need to be listed
     */
    public static void listByPriority(PriorityQueue<Process> pq) {
        PriorityQueue<Process> pqByPriority = new PriorityQueue<>(pq.size(), new PriorityComparator());
        for (Process p: pq) {
            if (p.getStatus() == RUNNING) {
                pqByPriority.add(p);
            }
        }

        while(!pqByPriority.isEmpty()){
            System.out.println( pqByPriority.remove());
        }
    }

    /**
     *
     * @param pq Priority Queue all process to be listed timeline wise
     */
    public static void listByTimestamp(PriorityQueue<Process> pq) {
        PriorityQueue<Process> pqByTimestamp = new PriorityQueue<>(pq.size(), new FIFOComparator());
        for (Process p: pq) {
            if (p.getStatus() == RUNNING) {
                pqByTimestamp.add(p);
            }
        }
        while(!pqByTimestamp.isEmpty()){
            System.out.println( pqByTimestamp.remove());
        }
    }

    /**
     * @param p  process which needs to be killed
     * @param pq Priority Queue from where Processes needs to be removed
     */
    public static void kill(Process p, PriorityQueue<Process> pq) {
        Iterator iterator = pq.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(p)) {
                p.kill();
                pq.remove(p);
                return;
            }
        }
    }

    /**
     * @param priority priority of the process to be killed
     * @param pq       Priority Queue from where Processes needs to be removed
     *  It removes all process with specific priority
     */
    public static void killWithPriority(Priority priority, PriorityQueue<Process> pq) {
        pq.removeIf(process -> process.getPriority().equals( priority));
    }

    /**
     * @param pq Priority Queue from where Processes needs to be removed
     *  It removes all Running Processes in PQ
     */
    public static void killRunningProcess(PriorityQueue<Process> pq) {
        pq.removeIf(process -> process.getStatus() == (RUNNING));
        pq.size();
    }

}

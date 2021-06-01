package iptiq;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTaskManager2 {
    private static TaskManager2 tm2;
    private static Process process1;
    private static Process process2;
    private static Process process3;
    private static Process process4;

    /**
     * Instantiate.
     */
    @Before
    public void instantiate(){
        tm2 = new TaskManager2(3);
        process1 = new Process(Priority.MEDIUM);
        process2 = new Process(Priority.HIGH);
        process3 = new Process(Priority.LOW);
        process4 = new Process(Priority.LOW);

        tm2.add(process1);
        tm2.add(process2);
        tm2.add(process3);
    }

    /**
     * add process through taskManager1
     */
    @Test
    public void _01_addProcess() throws Exception {
        assertEquals(3, tm2.getSortByTimestampPQ().size());
    }

    /**
     * add process more than capacity
     */
    @Test
    public void _02_addProcessMoreThanCapacity() throws Exception {
        tm2.add(process4);
        assertEquals(3, tm2.getSortByTimestampPQ().size());
        assertTrue(tm2.getSortByTimestampPQ().contains(process4));
        assertFalse(tm2.getSortByTimestampPQ().contains(process1));
    }

    /**
     * List processes in Priority Queue
     */
    @Test
    public void _03_listProcess() {
        try {
            System.out.println("_03_listProcess");

            TaskManagerInterface.list(tm2.getSortByTimestampPQ());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    /**
     * List processes in Priority Queue by lowest priority
     */
    @Test
    public void _04_listProcessByPriority() {
        try {
            System.out.println("_04_listProcessByPriority");
            TaskManagerInterface.listByPriority(tm2.getSortByTimestampPQ());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    /**
     * List processes in Priority Queue by timestamps
     */
    @Test
    public void _05_listProcessByTimestamp() {
        try {
            System.out.println("_05_listProcessByTimestamp");
            TaskManagerInterface.listByTimestamp(tm2.getSortByTimestampPQ());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }


    /**
     * kill process in priority Queue
     */
    @Test
    public void _06_killProcess() {
        TaskManagerInterface.kill(process1, tm2.getSortByTimestampPQ());
        assertEquals(2, tm2.getSortByTimestampPQ().size());
        assertTrue(tm2.getSortByTimestampPQ().contains(process2));
        assertTrue(tm2.getSortByTimestampPQ().contains(process3));
        TaskManagerInterface.kill(process2, tm2.getSortByTimestampPQ());
        TaskManagerInterface.kill(process3, tm2.getSortByTimestampPQ());
        assertEquals(0, tm2.getSortByTimestampPQ().size());
    }

    /**
     * kill all process in priority Queue with a specific
     */
    @Test
    public void _07_killAllProcessByPriority() {
        assertEquals(3, tm2.getSortByTimestampPQ().size());
        TaskManagerInterface.killWithPriority(Priority.HIGH, tm2.getSortByTimestampPQ());
        assertEquals(2, tm2.getSortByTimestampPQ().size());
    }

    /**
     * kill all process in priority Queue
     */
    @Test
    public void _08_killAllRunningProcess() {
        assertEquals(3, tm2.getSortByTimestampPQ().size());
        TaskManagerInterface.killRunningProcess(tm2.getSortByTimestampPQ());
        assertEquals(0, tm2.getSortByTimestampPQ().size());
    }

}

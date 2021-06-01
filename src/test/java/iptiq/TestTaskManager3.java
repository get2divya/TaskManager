package iptiq;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTaskManager3 {
    private static TaskManager3 tm3;
    private static Process process1;
    private static Process process2;
    private static Process process3;
    private static Process process4;

    /**
     * Instantiate.
     */
    @Before
    public void instantiate() throws Exception {
        tm3 = new TaskManager3(3);
        process1 = new Process(Priority.MEDIUM);
        process2 = new Process(Priority.HIGH);
        process3 = new Process(Priority.LOW);
        process4 = new Process(Priority.LOW);

        tm3.add(process1);
        tm3.add(process2);
        tm3.add(process3);
    }

    /**
     * add process through taskManager3
     */
    @Test
    public void _01_addProcess() throws Exception {
        assertEquals(3, tm3.getSortByPriorityPQ().size());
    }

    /**
     * add process more than capacity
     */
    @Test
    public void _02_addProcessMoreThanCapacity() throws Exception {
        tm3.add(process4);
        assertEquals(3, tm3.getSortByPriorityPQ().size());
        assertTrue(tm3.getSortByPriorityPQ().contains(process4));
        assertFalse(tm3.getSortByPriorityPQ().contains(process3));
    }

    /**
     * List processes in Priority Queue
     */
    @Test
    public void _03_listProcess() {
        try {
            System.out.println("_03_listProcess");
            TaskManagerInterface.list(tm3.getSortByPriorityPQ());
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
            TaskManagerInterface.listByPriority(tm3.getSortByPriorityPQ());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    /**
     * List processes in Priority Queue by priority
     */
    @Test
    public void _05_listProcessByTimestamp() {
        try {
            System.out.println("_05_listProcessByTimestamp");
            TaskManagerInterface.listByTimestamp(tm3.getSortByPriorityPQ());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }


    /**
     * kill process in priority Queue
     */
    @Test
    public void _06_killProcess() {
        TaskManagerInterface.kill(process1, tm3.getSortByPriorityPQ());
        assertEquals(2, tm3.getSortByPriorityPQ().size());
        assertTrue(tm3.getSortByPriorityPQ().contains(process2));
        assertTrue(tm3.getSortByPriorityPQ().contains(process3));
        TaskManagerInterface.kill(process2, tm3.getSortByPriorityPQ());
        TaskManagerInterface.kill(process3, tm3.getSortByPriorityPQ());
        assertEquals(0, tm3.getSortByPriorityPQ().size());
    }

    /**
     * kill all process in priority Queue with specific priority
     */
    @Test
    public void _07_killAllProcessByPriority() {
        assertEquals(3, tm3.getSortByPriorityPQ().size());
        TaskManagerInterface.killWithPriority(Priority.HIGH, tm3.getSortByPriorityPQ());
        assertEquals(2, tm3.getSortByPriorityPQ().size());
    }

    /**
     * kill all process in priority Queue
     */
    @Test
    public void _08_killAllRunningProcess() {
        assertEquals(3, tm3.getSortByPriorityPQ().size());

        TaskManagerInterface.killRunningProcess(tm3.getSortByPriorityPQ());
        assertEquals(0, tm3.getSortByPriorityPQ().size());
    }

}

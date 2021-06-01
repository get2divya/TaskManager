package iptiq;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTaskManager1 {
    private static TaskManager1 tm1;
    private static Process process1;
    private static Process process2;
    private static Process process3;
    private static Process process4;

    /**
     * Instantiate.
     */
    @Before
    public void instantiate() throws Exception {
        tm1 = new TaskManager1(3);
        process1 = new Process(Priority.MEDIUM);
        process2 = new Process(Priority.HIGH);
        process3 = new Process(Priority.LOW);
        process4 = new Process(Priority.LOW);

        tm1.add(process1);
        tm1.add(process2);
        tm1.add(process3);
    }

    /**
     * add process through taskManager1
     */
    @Test
    public void _01_addProcess() throws Exception {
        assertEquals(3, tm1.getPq().size());
    }

    /**
     * add process more than capacity
     */
    @Test(expected = Exception.class)
    public void _02_addProcessMoreThanCapacity() throws Exception {
        tm1.add(process4);
    }

    /**
     * List processes in Priority Queue
     */
    @Test
    public void _03_listProcess() {
        try {
            TaskManagerInterface.list(tm1.getPq());
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
            TaskManagerInterface.listByPriority(tm1.getPq());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    /**
     * List processes in Priority Queue by timestamp
     */
    @Test
    public void _05_listProcessByTimestamp() {
        try {
            TaskManagerInterface.listByTimestamp(tm1.getPq());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }


    /**
     * kill process in priority Queue
     */
    @Test
    public void _06_killProcess() {
        TaskManagerInterface.kill(process1, tm1.getPq());
        assertEquals(2, tm1.getPq().size());
        assertTrue(tm1.getPq().contains(process2));
        assertTrue(tm1.getPq().contains(process3));
        TaskManagerInterface.kill(process2, tm1.getPq());
        TaskManagerInterface.kill(process3, tm1.getPq());
        assertEquals(0, tm1.getPq().size());
    }

    /**
     * kill all process in priority Queue with a specific priority
     */
    @Test
    public void _07_killAllProcessByPriority() {
        assertEquals(3, tm1.getPq().size());
        TaskManagerInterface.killWithPriority(Priority.HIGH, tm1.getPq());
        assertEquals(2, tm1.getPq().size());
    }

    /**
     * kill all running process in priority Queue
     */
    @Test
    public void _08_killAllRunningProcess() {
        assertEquals(3, tm1.getPq().size());

        TaskManagerInterface.killRunningProcess(tm1.getPq());
        assertEquals(0, tm1.getPq().size());
    }
}


package ohha.puzzlecrafter.auxiliary;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SingleTimeEntryQueueTest {
    
    private SingleTimeEntryQueue<Integer> queue;
    
    @Before
    public void setUp() {
        queue = new SingleTimeEntryQueue<>();
    }
    
    
    @Test
    public void isEmptyReturnsTrueOnEmptyQueue() {
        assertTrue(queue.isEmpty());
    }
    @Test
    public void isEmptyReturnsFalseOnNonemptyQueue() {
        queue.addIfUnadded(0);
        assertFalse(queue.isEmpty());
    }
    
    @Test
    public void nullIsReturnedWhenDequeueingEmptyQueue() {
        assertEquals(null, queue.dequeue());
    }
    
    @Test
    public void newElementIsAddedIntoQueue() {
        queue.addIfUnadded(0);
        assertFalse(queue.isEmpty());
    }
    @Test
    public void elementIsNotAddedIntoQueueTwice() {
        queue.addIfUnadded(0);
        queue.addIfUnadded(0);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }
}

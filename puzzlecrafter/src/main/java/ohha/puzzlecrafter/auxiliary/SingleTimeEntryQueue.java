
package ohha.puzzlecrafter.auxiliary;

import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;


/**
 * Represents a queue into which elements may be entered only once, regardless
 * of whether they've already exited the queue or not.
 * Made for use in floodfill algorithms, see
 * {@link ohha.puzzlecrafter.puzzles.Fillomino}.
 */
public class SingleTimeEntryQueue<T> {
    
    private Set<T> entered;
    private Queue<T> queue;
    
    
    /**
     * Constructs an empty new <code>SingleTimeEntryQueue</code>.
     */
    public SingleTimeEntryQueue() {
        entered = new HashSet<>();
        queue = new LinkedList<>();
    }
    
    
    /**
     * Returns whether the queue is empty or not.
     * 
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    
    /**
     * Adds the given element into the queue if it hasn't already been added
     * before.
     * 
     * @param t the element to be added 
     */
    public void addIfUnadded(T t) {
        if (!entered.contains(t)) {
            entered.add(t);
            queue.add(t);
        }
    }
    
    
    /**
     * Removes the element at the head of the queue and returns it.
     * 
     * @return  the head of the queue
     */
    public T dequeue() {
        return queue.poll();
    }
}

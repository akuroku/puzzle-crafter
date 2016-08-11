
package ohha.puzzlecrafter.auxiliary;

import ohha.puzzlecrafter.grid.Grid;
import ohha.puzzlecrafter.grid.Cell;

import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;


public class SingleTimeEntryQueue<T> {
    
    private Set<T> entered;
    private Queue<T> queue;
    
    
    public SingleTimeEntryQueue() {
        entered = new HashSet<>();
        queue = new LinkedList<>();
    }
    
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    
    public void addIfUnadded(T t) {
        if (!entered.contains(t)) {
            entered.add(t);
            queue.add(t);
        }
    }
    
    
    public T dequeue() {
        return queue.poll();
    }
}

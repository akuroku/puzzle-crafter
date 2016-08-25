
package ohha.puzzlecrafter;


/**
 * Not implemented yet.
 * <p>
 * Acts as a general puzzle clue, so that each puzzle may use as its clues a
 * custom concrete implementation.
 */
public abstract class Clue {
    /**
     * Not implemented yet.
     * <p>
     * Returns whether the clue is being contradicted or not.
     * If a clue is contradicted, the puzzle can't be completed into a valid
     * solution.
     * 
     * @return  true if the clue is contradicted, false otherwise
     */
    public abstract boolean isContradicted();
}


package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;

public class Region {
    
    private Set<Cell> cells;
    
    
    public Region() {
        cells = new HashSet<>();
    }
    
    
    public void addCell(Cell c) {
        cells.add(c);
    }
    
    public Set<Cell> getCells() {
        return cells;
    }
    
    
    public boolean contains(Cell c) {
        return cells.contains(c);
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + 73 * cells.stream().mapToInt(c -> c.getX() + 2 * c.getY()).sum();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Region other = (Region) obj;
        
        if (cells.size() != other.getCells().size()) {
            return false;
        }
        return cells.containsAll(other.getCells());
    }
    
    
    @Override
    public String toString() {
        String s = "";
        for (Cell c : getCells()) {
            s += c.toString() + ", ";
        }
        return s;
    }
}

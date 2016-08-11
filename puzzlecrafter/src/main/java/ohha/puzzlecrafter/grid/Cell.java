
package ohha.puzzlecrafter.grid;


public class Cell {
    
    private final int x;
    private final int y;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    
    public Cell shiftX(int shift) {
        return new Cell(this.x + shift, this.y);
    }
    public Cell shiftY(int shift) {
        return new Cell(this.x, this.y + shift);
    }
    
    
    public Cell switchX(int newx) {
        return new Cell(newx, this.y);
    }
    public Cell switchY(int newy) {
        return new Cell(this.x, newy);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Cell other = (Cell) obj;
        return this.x == other.x && this.y == other.y;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.x;
        hash = 73 * hash + this.y;
        return hash;
    }
    
    
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}

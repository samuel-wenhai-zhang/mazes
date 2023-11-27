/**
 * Represents a single space in a Maze
 */
public class Node {
    private int row;
    private int col;
    private char ch;
    private Node parent;

    /**
     * Default constructor, creates a Wall node with the indicated
     * row and column
     * @param r
     * @param c
     */
    public Node(int r, int c) {
        row = r;
        col = c;
        ch = '#';
    }

    /**
     * Sets the parent of this node to the indicated node
     * (for use in BFS)
     */
    public void setParent(Node p) {
        parent = p;
    }

    /**
     * Returns the parent of this node
     * (for use in BFS)
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the character of this node
     * to 'S' indicating it is the start node
     */
    public void setStart() {
        ch = 'S';
    }


    /**
     * Sets the character of this node
     * to 'F' indicating it is the final node
     */
    public void setEnd() {
        ch = 'F';
    }


    /**
     * Sets the character of this node 
     * to '#' indicating it is a wall
     */
    public void setWall() {
        ch = '#';
    }

    /**
     * Sets the character of this node
     * to ' ' indicating it is empty
     */
    public void clearWall() {
        ch = ' ';
    }

    /** 
     * Sets the character of this node
     * to '.' indicating it has been 
     * visitied
     */
    public void setVisited() {
        ch = '.';
    }

    /**
     * Sets the character of this node 
     * to ' ' indicating it is empty 
     */
    public void clearVisited() {
        ch = ' ';
    }


    /**
     * Returns true if this space is a wall
     */
    public boolean isWall() {
        return ch == '#';
    }

    /**
     * Returns true if this space has been visited
     */
    public boolean isVisited() {
        return ch == '.';
    }

    /**
     * Returns true if this is the start
     */
    public boolean isStart() {
        return ch == 'S';
    }

    /**
     * Returns true if this is the end
     */
    public boolean isEnd() {
        return ch == 'F';
    }

    /**
     * Returns true if this space is not a wall
     * and has not been visited
     */
    public boolean isEmpty() {
        return ch == ' ' || ch == 'F' || ch == 'S';
    }

    public String toString() {
        return "" + ch;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
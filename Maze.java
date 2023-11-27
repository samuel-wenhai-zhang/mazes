/**
 * Maze generator adapted from code found at 
 * https://thenerdshow.com/maze.html 
 * by Chad Purdy
 */

/**
 * A class used to represent a maze
 */
public class Maze {

    private Node[][] maze;

    /**
     * Creates a random maze with the indicated width and height
     * Note: width and height are actually twice as large and always 
     * odd (to account for walls on the outer border)
     */
    public Maze(int width, int height) {
        maze = new Node[2 * height + 1][2 * width + 1];
        for (int i = 0; i < 2 * height + 1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                maze[i][j] = new Node(i, j);
            }
        }
        mazeStep(1, 1);
        maze[1][0].setStart();
        maze[maze.length - 2][maze[0].length - 1].setEnd();
    }

    /**
     * Recursive method for clearing out the empty spaces in the maze
     * 
     * @param r number of rows in the maze
     * @param c number of columns in the maze
     */
    public void mazeStep(int r, int c) {

        // 3 directions to step (can't step back)
        int[][] vector = new int[3][2]; // each direction, row/col
        int i;

        while (true) {
            i = 0; // create a list of possible options
            if (r > 1 && !maze[r - 2][c].isEmpty()) {
                vector[i][0] = r - 2;
                vector[i][1] = c;
                i++;
            }

            if (r < maze.length - 3 && !maze[r + 2][c].isEmpty()) {
                vector[i][0] = r + 2;
                vector[i][1] = c;
                i++;
            }

            if (c > 1 && !maze[r][c - 2].isEmpty()) {
                vector[i][0] = r;
                vector[i][1] = c - 2;
                i++;
            }

            if (c < maze[0].length - 3 && !maze[r][c + 2].isEmpty()) {
                vector[i][0] = r;
                vector[i][1] = c + 2;
                i++;
            }

            // i is never > 3 because the path behind this step
            // is cleared already

            if (i == 0) // check for dead end
                break;

            i = (int) (i * Math.random()); // select a random direction

            maze[(vector[i][0] + r) / 2][(vector[i][1] + c) / 2].clearWall(); // clear that block
            maze[vector[i][0]][vector[i][1]].clearWall(); // clear up to it
            mazeStep(vector[i][0], vector[i][1]); // next

        }

    }

    /**
     * Get the node at the indicated position in the maze
     * 
     * @param r row number
     * @param c column number
     * @return a Node at the indicated position
     */
    public Node getPos(int r, int c) {
        if (r < 0 || c < 0 || r > maze.length - 1 || c > maze[0].length - 1)
            return null;
        return maze[r][c];
    }

    /**
     * Sets the indicated row and column to the symbol for visited
     * 
     * @param r
     * @param c
     */
    public void setVisited(int r, int c) {
        if (r >= 0 && c >= 0 && r <= maze.length - 1 && c <= maze[0].length - 1)
            maze[r][c].setVisited();
    }

    /**
     * Sets the indicated row and column to blank
     * 
     * @param r
     * @param c
     */
    public void clearVisited(int r, int c) {
        if (r >= 0 && c >= 0 && r <= maze.length - 1 && c <= maze[0].length - 1)
            maze[r][c].clearVisited();
    }

    public int getWidth() {
        return maze[0].length;
    }

    public int getHeight() {
        return maze.length;
    }

    public void display() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Resets the visited spaces back to blanks and the start and finish nodes back
     * to S and F.
     */
    public void reset() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j].isVisited())
                    maze[i][j].clearVisited();
            }
        }
        maze[1][0].setStart();
        maze[maze.length - 2][maze[0].length - 1].setEnd();
    }

}
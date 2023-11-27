
public class MazeTester {

    public static void main(String[] args) {
        Maze maze = new Maze(10,10);
        maze.display();
        // MazeSolver.RecDFSolve(maze, 1, 0);
        // MazeSolver.IterDFSolve(maze);
        MazeSolver.BFSolve(maze);
        maze.display();
    }

}
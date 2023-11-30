import java.util.Stack;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MazeSolver {

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean RecDFSolve(Maze maze, int r, int c, boolean animated) {        
        Node node = maze.getPos(r,c);
        if (node == null) {
            return false;
        }
        if (node.isEnd()) {
            return true;
        }
        if (node.isWall() || node.isVisited()) {
            return false;
        }
        node.setVisited();
        if (animated) {
            clearScreen();
            maze.display();
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (RecDFSolve(maze, r + 1, c, animated) || RecDFSolve(maze, r, c + 1, animated) || RecDFSolve(maze, r - 1, c, animated) || RecDFSolve(maze, r, c - 1, animated)) {
            return true;
        }
        node.clearVisited();
        return false;
    }
    
    public static void IterDFSolve(Maze maze) {
        Stack<Node> unvisited = new Stack<Node>();
        Set<Node> visited = new HashSet<Node>();
        unvisited.push(maze.getPos(1, 0));
        while (!unvisited.isEmpty()) {
            Node node = unvisited.pop();
            if (node.isEnd()) {
                Node parent = node.getParent();
                while (parent != null) {
                    parent.setVisited();
                    parent = parent.getParent();
                }
                break;
            }
            if (!visited.contains(node)) {
                visited.add(node);
                int r = node.getRow();
                int c = node.getCol();
                Node left = maze.getPos(r, c-1);
                Node up = maze.getPos(r - 1, c);
                Node right = maze.getPos(r, c + 1);
                Node down = maze.getPos(r + 1, c);
                if (left != null && !left.isWall() && !visited.contains(left)) {
                    left.setParent(node);
                    unvisited.push(left);
                }
                if (up != null && !up.isWall() && !visited.contains(up)) {
                    up.setParent(node);
                    unvisited.push(up);
                }
                if (right != null && !right.isWall() && !visited.contains(right)) {
                    right.setParent(node);
                    unvisited.push(right);
                }
                if (down != null && !down.isWall() && !visited.contains(down)) {
                    down.setParent(node);
                    unvisited.push(down);
                }
            }
        }
    }
    
    public static void BFSolve(Maze maze) {
        Queue<Node> unvisited = new LinkedList<Node>();
        Set<Node> visited = new HashSet<Node>();
        unvisited.add(maze.getPos(1, 0));
        while (!unvisited.isEmpty()) {
            Node node = unvisited.remove();
            if (node.isEnd()) {
                Node parent = node.getParent();
                while (parent != null) {
                    parent.setVisited();
                    parent = parent.getParent();
                }
                break;
            }
            if (!visited.contains(node)) {        
                visited.add(node);
                int r = node.getRow();
                int c = node.getCol();
                Node down = maze.getPos(r + 1, c);
                Node right = maze.getPos(r, c + 1);
                Node up = maze.getPos(r - 1, c);
                Node left = maze.getPos(r, c-1);
                
                if (down != null && !down.isWall() && !visited.contains(down)) {
                    down.setParent(node);
                    unvisited.add(down);
                }
                if (right != null && !right.isWall() && !visited.contains(right)) {
                    right.setParent(node);
                    unvisited.add(right);
                }
                if (up != null && !up.isWall() && !visited.contains(up)) {
                    up.setParent(node);
                    unvisited.add(up);
                }
                if (left != null && !left.isWall() && !visited.contains(left)) {
                    left.setParent(node);
                    unvisited.add(left);
                }
            }
        }
    }
}

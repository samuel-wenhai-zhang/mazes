import java.util.Stack;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MazeSolver {
    public static boolean RecDFSolve(Maze maze, int r, int c) {
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
        return RecDFSolve(maze, r + 1, c) || RecDFSolve(maze, r, c + 1) || RecDFSolve(maze, r - 1, c) || RecDFSolve(maze, r, c - 1);
    }
    
    public static void IterDFSolve(Maze maze) {
        Stack<Node> unvisited = new Stack<Node>();
        Set<Node> visited = new HashSet<Node>();
        unvisited.push(maze.getPos(1, 0));
        while (!unvisited.isEmpty()) {
            Node node = unvisited.pop();
            if (node == null || node.isWall()) {
                continue;
            }
            if (node.isEnd()) {
                break;
            }
            int r = node.getRow();
            int c = node.getCol();
            if (!visited.contains(node)) {
                visited.add(node);
                unvisited.push(maze.getPos(r, c - 1));
                unvisited.push(maze.getPos(r - 1, c));
                unvisited.push(maze.getPos(r, c + 1));
                unvisited.push(maze.getPos(r + 1, c));
                node.setVisited();
            }
        }
    }
    
    public static void BFSolve(Maze maze) {
        Queue<Node> unvisited = new LinkedList<Node>();
        Set<Node> visited = new HashSet<Node>();
        unvisited.add(maze.getPos(1, 0));
        while (!unvisited.isEmpty()) {
            Node node = unvisited.remove();
            if (node.isWall()) {
                continue;
            }
            if (node.isEnd()) {
                Node parent = node.getParent();
                while (parent != null) {
                    parent.setVisited();
                    parent = parent.getParent();
                }
                break;
            }
            int r = node.getRow();
            int c = node.getCol();
            if (!visited.contains(node)) {
                visited.add(node);
                Node left = maze.getPos(r, c-1);
                Node up = maze.getPos(r - 1, c);
                Node right = maze.getPos(r, c + 1);
                Node down = maze.getPos(r + 1, c);
                
                if (left != null) {
                    left.setParent(node);
                    unvisited.add(left);
                }
                if (up != null) {
                    up.setParent(node);
                    unvisited.add(up);
                }
                if (right != null) {
                    right.setParent(node);
                    unvisited.add(right);
                }
                if (down != null) {
                    down.setParent(node);
                    unvisited.add(down);
                }
            }
        }
    }
}

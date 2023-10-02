// import java.util.Stack;

public class SolveMaze {
    Maze maze;
    Rat rat;

    public static void main(String[] args) {
        new SolveMaze();
    }

    public SolveMaze() {
        maze = new Maze("maze.txt");
        // System.out.println(maze);
        rat = new Rat(maze);
        maze.print();

        if (rat.solveMaze()) {
            System.out.println("Maze solved!");
        } else {
            System.out.println("No path found.");
        }

        maze.print();
    }
}

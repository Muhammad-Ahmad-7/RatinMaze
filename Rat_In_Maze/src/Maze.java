import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {
    int m, n;
    int[][] a;
    private static final int OPEN = 0, TRIED = 2, PATH = 3;

    public Maze(String filename) {
        try {
            Scanner scanner = new Scanner(
                    new File("/home/muhammad/Comsats/3rd Semester/DSA/Assignment # 1/Solution/Rat_In_Maze/maze.txt"));
            // System.out.println(scanner);
            m = scanner.nextInt();
            n = scanner.nextInt();
            // System.out.println(m);
            // System.out.println(n);
            a = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOpen(Location location) {
        int x = location.getX();
        int y = location.getY();

        // Check if the location is within the bounds of the maze
        if (x >= 0 && x < n && y >= 0 && y < m) {
            // a[location.getY()][location.getX()] = OPEN;
            return (a[y][x] == OPEN);
        } else {
            // Location is out of bounds, treat it as a WALL
            return false;
        }
    }

    public void markMoved(Location location) {
        a[location.getY()][location.getX()] = PATH;
    }

    public void markTried(Location location) {
        a[location.getY()][location.getX()] = TRIED;
    }

    public int getWidth() {
        return n;
    }

    public int getHeight() {
        return m;
    }

    public void print() {
        char[] chars = { ' ', '+', '?', 'o' };
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(chars[a[i][j]] + " ");
            }
            System.out.println(); // Add a newline after printing each row
        }
    }

}

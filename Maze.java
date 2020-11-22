import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Maze Class
 */
public class Maze {
    private int[][] maze;               /** 2D array with maze data */
    private boolean[][] visited;        /** 2D array with visited cells */
    private int height, width;          /** Maze Height and Width */
    private Coordinates start, exit;    /** Starting and Exiting points */

    //region GET Methods
    public int[][] getMaze() {
        return maze;
    }

    public Coordinates getStart() {
        return start;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean[][] getVisited() {
        return visited;
    }
    //endregion

    /**
     * Initialize the 2D arrays with default values
     */
    private void initizalize() {
        this.maze = new int[this.height][this.width];
        this.visited = new boolean[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.visited[i][j] = false;
            }
        }
    }

    /**
     * Maze constructor
     *
     * Sets the maze (2D array) with the proper values
     * Sets the starting and exiting points
     *
     * @param mazefile Image to import data from
     */
    public Maze(BufferedImage mazefile) {
        this.height = mazefile.getHeight();
        this.width = mazefile.getWidth();
        this.initizalize();

        /* Fill the 2D array:
            0 - path
            1 - wall
         */
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (mazefile.getRGB(x,y) == -1) {
                    this.maze[y][x] = 0;
                } else {
                    this.maze[y][x] = 1;
                }
            }
        }

        // get the starting point
        for (int x = 0; x < this.width; x++) {
            if (this.maze[0][x] == 0) {
                this.start = new Coordinates(x, 0);
                break;
            }
        }

        // get the exit point
        for (int x = 0; x < this.width; x++) {
            if (this.maze[height - 1][x] == 0) {
                this.exit = new Coordinates(x, height - 1);
                break;
            }
        }
    }

    /**
     * Recursive backtracking algorithm to find the solution for the maze
     * @param x position X
     * @param y position Y
     * @return true if there is a solution
     */
    public boolean findGoal(int x, int y) {
        if (y == exit.getY() && x == exit.getX()) {
            this.visited[y][x] = true;
            return true;
        }
        if (maze[y][x] == 1 || visited[y][x]) {
            return false;
        }
        else {
            this.visited[y][x] = true;

            if (y < this.height - 1) {
                if (this.findGoal(x, y + 1)) {
                    return true;
                }
            }
            if (y > 0) {
                if (this.findGoal(x, y - 1)) {
                    return true;
                }
            }

            if (x < this.width - 1) {
                if (this.findGoal(x + 1,y)) {
                    return true;
                }
            }
            if (x > 0) {
                if (this.findGoal(x - 1, y)) {
                    return true;
                }
            }

            this.visited[y][x] = false;
            return false;
        }
    }
    
    public boolean findGoal(Coordinates start) {
    	
    	return findGoal(start.getX(), start.getY());
    	
    }

    /**
     * Prints the maze
     */
    public void printMaze() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.maze[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the solution for the maze
     * You may want to call the "findGoal" method first
     */
    public void printSolution() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (visited[y][x]) {
                    System.out.print("* ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Exports the solution to a PNG file (similar to the original file, on the "solutions" directory"
     * @param filename  filename to export the image
     * @return  true upon success
     */
    public boolean exportSolution(File filename) {
        BufferedImage solution = new BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (visited[y][x]) {
                    solution.setRGB(x, y, new Color(255,255,255).getRGB());
                }
                else if (maze[y][x] == 1) {
                    solution.setRGB(x, y, new Color(0,0,0).getRGB());
                }
                else {
                    solution.setRGB(x, y, new Color(150,150,150).getRGB());
                }
            }
        }

        try {
            ImageIO.write(solution, "PNG", filename);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

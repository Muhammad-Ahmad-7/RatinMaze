public class Rat {
    private Maze maze;
    private Location location;

    public Rat(Maze maze) {
        this.maze = maze;
        location = new Location(1, 1); // Starting location
    }

    public Location getLocation() {
        return (Location) location.clone();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean canMove(int direction) {
        Location neighbor = location.adjacent(direction);
        return maze.isOpen(neighbor);
    }

    public void move(int direction) {
        Location neighbor = location.adjacent(direction);
        location = neighbor;
        maze.markMoved(location);
    }

    public void tried(int direction) {
        Location triedLocation = location.adjacent(direction);
        location = triedLocation;
        maze.markTried(location);
    }

    public boolean solveMaze() {
        if (isOut()) {
            return true; // Maze is solved
        }

        for (int direction = Direction.NORTH; direction <= Direction.WEST; direction++) {
            if (canMove(direction)) {
                move(direction);
                if (solveMaze()) {
                    return true; // Found a path to the exit
                } else {
                    // Mark the current cell as a dead-end during backtracking

                    // maze.markTried(location);
                    // Backtrack by moving in the opposite direction
                    int oppositeDirection = getOppositeDirection(direction);
                    move(oppositeDirection);
                }
            }
        }
        return false; // No path found in any direction, trigger backtracking
    }

    private int getOppositeDirection(int direction) {
        // Map each direction to its opposite
        switch (direction) {
            case Direction.NORTH:
                return Direction.SOUTH;
            case Direction.EAST:
                return Direction.WEST;
            case Direction.SOUTH:
                return Direction.NORTH;
            case Direction.WEST:
                return Direction.EAST;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    public boolean isOut() {
        Location currentLocation = getLocation();
        return currentLocation.getX() == maze.getWidth() - 1 && currentLocation.getY() == maze.getHeight() - 2;
    }
}

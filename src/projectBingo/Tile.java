package projectBingo;

/**
 * Tiles are the squares on a bingo board. There are 25 tiles.
 * 
 * A tile has:
 *  A completion state (uncompleted, player1, player2)
 *  A goal
 *  A priority
 */
public class Tile implements Comparable<Tile>{

    private int state;
    private int priority;
    private final String goal;

    /**
     * Constructs a new Tile
     */
    public Tile (String goal) {
        this.goal = goal;
        this.state = 0;
        this.priority = 1;
    }

    /**
     * Constructs a new Tile
     */
    public Tile (String goal, int priority) {
        this.goal = goal;
        this.state = 0;
        this.priority = priority;
    }

    /**
     * returns the completionState state of this Tile
     * @return this.state
     */
    public int getCompletionState() {
        return state;
    }

    /**
     * sets the completionState state of this Tile
     * @param state some completion state
     */
    public void setCompletionState(int state) {
        this.state = state;
    }

    /**
     * returns the String goal of this Tile
     * @return
     */
    public String getGoal() {
        return goal;
    }

    /**
     * sets the priority state of this Tile
     * @param state some completion state
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * returns the priority of this Tile
     * @return
     */
    public int getPriority() {
        return priority;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "goal: " + goal + " | state: " + state + " | priority: " + priority;
    }

    /**
     * Compares tiles by their completion state
     * @param other the other Tile to compare to
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    @Override
    public int compareTo(Tile other) {
        return this.priority - other.priority;
    }
}
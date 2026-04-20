package projectBingo;

/**
 * Board Class for creating the board. A Board has
 * A 5x5 Tile matrix
 */
public class Board {

    private Tile[][] matrix;

    /**
     * Constructs a new Board from a Bag of Tiles
     * @param bag a Bag of 25 Tiles
     */
    public Board(Bag bag) {
        this.matrix = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = bag.remove();
            }
        }
    }

    /**
     * Returns the tile at the given position
     * @param row the row index
     * @param col the column index
     * @return the Tile at [row][col]
     */
    public Tile getTile(int row, int col) {
        return matrix[row][col];
    }

    /**
     * Sets a tile at the given position
     * @param row the row index
     * @param col the column index
     * @param tile the Tile to place
     */
    public void setTile(int row, int col, Tile tile) {
        matrix[row][col] = tile;
    }

    /**
     * Prints the board to console
     */
    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Tile tile = matrix[i][j];
                System.out.printf("[%d,%d] %-40s state: %d%n", i + 1, j + 1, tile.getGoal(), tile.getCompletionState());
            }
            System.out.println();
        }
    }
}

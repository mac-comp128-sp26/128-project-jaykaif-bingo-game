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

    /**
     * Detects if a player has won the game
     * @return returns 1 if player 1 won, 2 if player 2 won, 0 if neither won
     */
    public int detectWin() {
        if (detectEqualness(matrix[0][0].getCompletionState(), matrix[1][1].getCompletionState(), matrix[2][2].getCompletionState(), matrix[3][3].getCompletionState(), matrix[4][4].getCompletionState())) {
            return matrix[0][0].getCompletionState();
        }
        if (detectEqualness(matrix[0][4].getCompletionState(), matrix[1][3].getCompletionState(), matrix[2][2].getCompletionState(), matrix[3][1].getCompletionState(), matrix[4][0].getCompletionState())) {
            return matrix[0][4].getCompletionState();
        }

        for (int i = 0; i < 5; i++) {
            if (detectEqualness(matrix[0][i].getCompletionState(), matrix[1][i].getCompletionState(), matrix[2][i].getCompletionState(), matrix[3][i].getCompletionState(), matrix[4][i].getCompletionState())) {
                return matrix[0][i].getCompletionState();
            }
            if (detectEqualness(matrix[i][0].getCompletionState(), matrix[i][1].getCompletionState(), matrix[i][2].getCompletionState(), matrix[i][3].getCompletionState(), matrix[i][4].getCompletionState())) {
                return matrix[i][0].getCompletionState();
            }
        }
        return 0;
    }

    /**
     * Returns true if all 5 paramaters are equal
     * 
     */
    public Boolean detectEqualness(int a,int b, int c, int d, int e) {
        if(a==b && a==c && a==d && a==e && b==c && b==d && b==e && c==d && c==e && d==e) {
            return true;
        }
        return false;
    }
}

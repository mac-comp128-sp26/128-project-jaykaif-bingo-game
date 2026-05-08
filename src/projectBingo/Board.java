package projectBingo;

public abstract class Board {
    

    /**
     * Returns the tile at the given position
     * @param row the row index
     * @param col the column index
     * @return the Tile at [row][col]
     */
    public abstract Tile getTile(int row, int col);

        /**
     * Sets a tile at the given position
     * @param row the row index
     * @param col the column index
     * @param tile the Tile to place
     */
    public abstract void setTile(int row, int col, Tile tile);

    /**
     * Prints the board to console
     */
    public abstract void print();

    /**
     * Detects if a player has won the game
     * @return 1 if player 1 won, 2 if player 2 won, 0 if neither won
     */
    public abstract int detectWin();

    /**
     * Updates the bitboards. If it is not a bitboard implementation it just does nothing
     * @param row
     * @param col
     */
    public void update(int row, int col) {}
}

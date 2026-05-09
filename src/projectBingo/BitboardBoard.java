package projectBingo;

import java.util.Arrays;
import java.util.Collections;

public class BitboardBoard extends Board {
    
    // Row masks: bits {r*5, r*5+1, r*5+2, r*5+3, r*5+4} 
    private static final int[] ROWMASKS = buildRowMasks();
 
    // Column masks: bits {c, c+5, c+10, c+15, c+20} 
    private static final int[] COLMASKS = buildColMasks();
 
    // Foward diaganol bits {0,6,12,18,24} 
    private static final int FOWARDMASK = (1 << 0) | (1 << 6) | (1 << 12) | (1 << 18) | (1 << 24);
 
    // Backwards diaganol bits {4,8,12,16,20} 
    private static final int BACKWARDSMASK = (1 << 4) | (1 << 8) | (1 << 12) | (1 << 16) | (1 << 20);


    //Store as a list instead of matrix
    private final Tile[] tiles;
 
    // Player 1 bitboard
    private int board1;
 
    // Player 2 bitboard
    private int board2;

    /**
     * Constructs a new Board from a Bag of Tiles
     * @param bag a Bag of Tiles
     */
    public BitboardBoard(Bag bag) {
        this.tiles = new Tile[25];
        this.board1 = 0;
        this.board2 = 0;

        for (int i = 0; i < 25; i++) {
            tiles[i] = bag.remove();
        }
        Collections.shuffle(Arrays.asList(this.tiles)); //this shuffle is needed for priority queue
    }

    /**
     * Returns the tile at the given position
     * @param row the row index
     * @param col the column index
     * @return the Tile at [row][col]
     */
    public Tile getTile(int row, int col) {
        return tiles[flattenIndex(row, col)];
    }

    /**
     * Sets a tile at the given position and syncs the bitboards to match the tile's current completion state.
     * @param row the row index
     * @param col the column index
     * @param tile the Tile to place
     */
    public void setTile(int row, int col, Tile tile) {
        int index = flattenIndex(row, col);
        tiles[index] = tile;
        syncBit(index, tile.getCompletionState()); 
    }

    /** 
     * Converts (row, col) to a flat bit index. 
     * Going to use this a lot so its easier to have a function for it
     */
    private static int flattenIndex(int row, int col) {
        return row * 5 + col;
    }

    /**
     * Sets the bit at index to 1 depending on completionState
     * @param index index of the bit to change
     * @param completionState Completionstate of the Tile at index
     */
    private void syncBit(int index, int completionState) {
        //adding a lot of comments ot this function cause its like wierd and complex for me
        int bit = 1 << index; //determines the bit of the index that we are changing
        board1 &= ~bit; //~bit flips the bits and &= forces the position of the bit to 0. I do this so that the two bit boards do not end up with the same bit being marked on both
        board2 &= ~bit;
        if (completionState == 1) {
            board1 |= bit; //|= Ors the board with the bit, so that the index gets set to 1.
        } else if (completionState == 2) {
            board2 |= bit;
        }
    }

    /**
     * Detects if a player has won the game using bit operations.
     * @return 1 if player 1 won, 2 if player 2 won, 0 if neither won
     */
    public int detectWin() {
        
        /**
         * So the way that bit operations work to detect wins is as follows
         * 
         * STEP 1: board & mask.
         *      Keeps only the bits that are in both board and mask
         * STEP 2: == mask
         *      If board contains the mask's bits, then it should currentlly be identical to mask if and only if there is a 5 in a row, otherwise if they are different, there is not a 5 in a row.
         */

        for (int mask : ROWMASKS) {
            if ((board1 & mask) == mask) return 1;
            if ((board2 & mask) == mask) return 2;
        }
 
        for (int mask : COLMASKS) {
            if ((board1 & mask) == mask) return 1;
            if ((board2 & mask) == mask) return 2;
        }
 
        if ((board1 & FOWARDMASK) == FOWARDMASK) return 1;
        if ((board2 & FOWARDMASK) == FOWARDMASK) return 2;
 
        if ((board1 & BACKWARDSMASK) == BACKWARDSMASK) return 1;
        if ((board2 & BACKWARDSMASK) == BACKWARDSMASK) return 2;
  


        // This part detects a stalemate win. IE p1 has 12 p2 has 13, but no rows.
        int count1 = Integer.bitCount(board1);
        int count2 = Integer.bitCount(board2);
 
        if (count1 + count2 == 25) {
            return count1 >= 13 ? 1 : 2;
        }
 
        return 0;
    }

    /**
     * Prints the board to console
     */
    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Tile tile = tiles[i*5 + j];
                System.out.printf("[%d,%d] %-40s state: %d%n", i + 1, j + 1, tile.getGoal(), tile.getCompletionState());
            }
            System.out.println();
        }
    }

    //builds masks for each row
    private static int[] buildRowMasks() {
        int[] masks = new int[5];
        for (int r = 0; r < 5; r++) {
            int mask = 0;
            for (int c = 0; c < 5; c++) {
                mask |= (1 << (r * 5 + c));
            }
            masks[r] = mask;
        }
        return masks;
    }
 
    //build masks for each col
    private static int[] buildColMasks() {
        int[] masks = new int[5];
        for (int c = 0; c < 5; c++) {
            int mask = 0;
            for (int r = 0; r < 5; r++) {
                mask |= (1 << (r * 5 + c));
            }
            masks[c] = mask;
        }
        return masks;
    }

    /**
     * Syncs the bitboard. Meant to be called after setting the completion state of a tile, because the bitboards need to be updated
     * If I had more time or was implementing the bitboards from the beginning I would have structured my code to where i dont need this function, but 
     * im adding this on last, and kinda just want a cheap and easy solution, even if it doesnt scale well.
     * @param row row index (0-4)
     * @param col column index (0-4)
     */
    public void update(int row, int col) {
        int index = flattenIndex(row, col);
        syncBit(index, tiles[index].getCompletionState());
    }
}

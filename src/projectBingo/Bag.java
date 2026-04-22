package projectBingo;

import java.util.List;

/**
 * Bag is meant to contain a queue of Tiles
 * 
 * Bag has
 * A queue of Tiles
 */
public abstract class Bag {

    private List<Tile> tiles; // need to initialize


    /**
     * Adds a tile to the bag
     * @param tile the Tile to add
     */
    public void add(Tile tile) {
        tiles.add(tile);
    }

    // null pointer exception??


    /**
     * Removes and returns the next tile
     * @return the next Tile
     */
    public abstract Tile remove();

    /**
     * Returns the number of tiles in the bag
     * @return the size of the bag
     */
    public int size() {
        return tiles.size();
    }

    /**
     * Returns true if the bag is empty
     * @return whether the bag is empty
     */
    public boolean isEmpty() {
        return tiles.isEmpty();
    }

}
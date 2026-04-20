package projectBingo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * RegularBag is meant to contain a queue of Tiles
 * 
 * RegularBag has
 * A queue of Tiles
 */
public class RegularBag extends Bag{

    private Queue<Tile> tiles;

    /**
     * Constructs a new empty Bag
     */
    public RegularBag() {
        this.tiles = new LinkedList<>();
    }

    /**
     * Constructs a new Bag from a List of Tiles
     * @param tiles a List of Tiles
     */
    public RegularBag(List<Tile> tiles) {
        this.tiles = new LinkedList<>(tiles);
    }

    /**
     * Removes and returns the next tile
     * @return the next Tile
     */
    public Tile remove() {
        return tiles.poll();
    }
}
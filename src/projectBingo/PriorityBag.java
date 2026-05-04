package projectBingo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * PriorityBag is meant to contain a priority queue of Tiles
 * 
 * PriorityBag has
 * A priority queue of Tiles
 */
public class PriorityBag extends Bag{

    private Queue<Tile> tiles;

    /**
     * Constructs a new empty Bag
     */
    public PriorityBag() {
        this.tiles = new LinkedList<>();
    }

    /**
     * Constructs a new Bag from a List of Tiles
     * @param tiles a List of Tiles
     */
    public PriorityBag(List<Tile> tiles) {
        this.tiles = new LinkedList<>(tiles);
    }

    /**
     * Removes and returns a random weighted tile
     * @return the next Tile
     */
    public Tile remove() {
        int totalPrio = 0;

        for (Tile tile : tiles) {
            totalPrio += tile.getPriority();
        }
        Random rand = new Random();

        int randInt = rand.nextInt(totalPrio); 

        for (Tile tile : tiles) {
            randInt -= tile.getPriority();
            if (randInt <= 0) {
                tiles.remove(tile);
                return tile;
            }
        }
        return null;
    }
}
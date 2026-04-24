package projectBingo;

import java.util.ArrayList;
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
        List<Tile> tempList = new ArrayList<>();
        Random rand = new Random();

        for (Tile tile : tiles) {
            for (int i = 0; i < tile.getPriority(); i++) {
                tempList.add(tile);
            }
        }

        int randInt = rand.nextInt(tempList.size()); 

        tiles.remove(tempList.get(randInt));

        return tempList.get(randInt);
    }
}
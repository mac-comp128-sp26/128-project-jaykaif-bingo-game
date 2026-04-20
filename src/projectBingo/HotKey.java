package projectBingo;

/**
 * Class for creating hotkeys. Hotkeys should have
 * A String instance variable (the hotkey)
 * A Lambda expression that gets called when the hotkey is pressed
 */
public class HotKey {

    private final String key;
    private final Runnable action;

    /**
     * Constructs a new HotKey
     * @param key the hotkey string
     * @param action the lambda expression to run when pressed
     */
    public HotKey(String key, Runnable action) {
        this.key = key;
        this.action = action;
    }

    /**
     * Triggers the hotkey action
     */
    public void press() {
        action.run();
    }

    /**
     * Returns the hotkey string
     * @return this.key
     */
    public String getKey() {
        return key;
    }
}

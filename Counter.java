package connect4;

/**
 * Counter class
 */
public class Counter {
    private Player player; // player that own's the counter

    /**
     * Constructor. Assigns the player object
     */
    public Counter(Player player) {
        this.player = player;
    }

    /**
     * Player getter
     * 
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    public String toString() {
        return player.getSymbol() + "";
    }

    /**
     * Compares given object with the current instance. If the other's object player
     * is the same as the instance's player - then return true
     */
    public boolean equals(Object o) {
        if (!(o instanceof Counter)) {
            return false;
        } else {

            Counter other = (Counter) o;

            if (player == other.getPlayer()) {
                return true;
            } else
                return false;
        }
    }

}
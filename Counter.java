public class Counter {
    private Player player;

    public Counter(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String toString() {
        return player.getSymbol() + "";
    }

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
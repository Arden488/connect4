package connect4;

/**
 * Player class
 */
public class Player {
    private String name; // Player's name
    private char symbol; // Player's counter symbol

    /**
     * Constructor Assigns name and symbol to the player
     */
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Name getter
     * 
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Counter symbol getter
     * 
     * @return symbol
     */
    public char getSymbol() {
        return symbol;
    }

    public String toString() {
        return name;
    }
}
package connect4;

import java.util.Random;

/**
 * Assessed Exercise 2 "Connect 4"
 * 
 * @author Anton Samoilov, MSc SD <2459087S@student.gla.ac.uk> 2459087S
 */
public class ConnectFour {
    public static void main(String[] args) {
        Board board = new Board(6, 7);
        Player p1 = new Player("Clive", 'o');
        Player p2 = new Player("Sharon", 'x');
        playRandomly(board, p1, p2);
    }

    /**
     * Plays randomly with given board and players
     */
    public static void playRandomly(Board board, Player p1, Player p2) {
        Player player = p1;
        Boolean firstPlayer = true;
        String message = "It's a draw!";

        while (!board.isFull()) {

            // Switch between players
            firstPlayer = !firstPlayer;
            player = firstPlayer ? p1 : p2;

            Random rand = new Random();
            int randomNum = rand.nextInt(7);

            board.add(new Counter(player), randomNum);

            if (board.isPlayerWon()) {
                message = "The winner is: " + player + " (" + player.getSymbol() + "). Win condition: "
                        + board.winCondition;
                break;
            }
        }

        System.out.println(board);
        System.out.println(message);
    }
}
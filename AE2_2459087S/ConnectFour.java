package connect4.AE2_2459087S;

import java.util.Random;

/**
 * Assessed Exercise 2 "Connect 4"
 * 
 * @author Anton Samoilov, MSc SD <2459087S@student.gla.ac.uk> 2459087S
 */
public class ConnectFour {
    public static void main(String[] args) {
        test1();

        System.out.println("___________________\n");

        test2();

        System.out.println("___________________\n");

        test3();

        System.out.println("___________________\n");

        test4();

        System.out.println("___________________\n");

        test5();
    }

    /**
     * Test task 1c
     */
    public static void test1() {
        Player p1 = new Player("Clive", 'x');
        Counter c = new Counter(p1);
        System.out.println(c.getPlayer().toString() + ", " + c.toString());
    }

    /**
     * Test task 2c
     */
    public static void test2() {
        Player p1 = new Player("Clive", 'x');
        Column col = new Column(4);
        for (int i = 0; i < 5; i++) {
            Boolean result = col.add(new Counter(p1));
            System.out.println(result);
        }
    }

    /**
     * Test task 2e
     */
    public static void test3() {
        Column col = new Column(6);
        Player p1 = new Player("Clive", 'o');
        Player p2 = new Player("Sharon", 'x');
        for (int i = 0; i < 3; i++) {
            col.add(new Counter(p1));
            col.add(new Counter(p2));
        }
        col.display();
    }

    /**
     * Test task 3d
     */
    public static void test4() {
        Board board = new Board(6, 7);
        Player p1 = new Player("Clive", 'o');
        Player p2 = new Player("Sharon", 'x');
        board.add(new Counter(p2), 6);
        board.add(new Counter(p1), 3);
        board.add(new Counter(p2), 4);
        board.add(new Counter(p1), 4);
        board.add(new Counter(p2), 5);
        board.add(new Counter(p1), 5);
        board.add(new Counter(p2), 6);
        board.add(new Counter(p1), 5);
        board.add(new Counter(p2), 6);
        board.add(new Counter(p1), 6);
        System.out.println(board);
    }

    /**
     * Test task 4
     */
    public static void test5() {
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
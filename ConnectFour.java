import java.util.Random;

/**
 * Assessed Exercise 2 "Connect 4"
 * 
 * @author Anton Samoilov, MSc SD <2459087S@student.gla.ac.uk> 2459087S
 */
public class ConnectFour {
    public static void main(String[] args) {
        play1(1);
        // play2();
    }

    public static void play2() {
        int[][] plays = { { 5, 1, 0, 1, 6, 1, 4, 5, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 2, 6, 6, 4, 0, 5, 4, 1, 5, 2, 4, 4, 2, 1, 3, 5, 2, 6, 1, 1, 2, 1, 0, 1, 5, 6, 3, 5, 5, 0, 2, 0, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 1, 0, 5, 6, 1, 0, 4, 1, 3, 4, 5, 4, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 2, 2, 3, 5, 5, 6, 5, 2, 6, 4, 3, 4, 5, 0, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 6, 6, 3, 5, 6, 2, 6, 2, 6, 3, 0, 3, 0, 6, 5, 4, 0, 3, 1, 5, 2, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 3, 5, 0, 6, 3, 2, 5, 1, 5, 3, 0, 6, 1, 2, 0, 3, 6, 5, 4, 6, 5, 6, 6, 3, 3, 0, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 2, 6, 3, 2, 4, 3, 2, 6, 2, 3, 0, 4, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 1, 3, 1, 0, 3, 6, 1, 0, 6, 1, 5, 0, 0, 4, 1, 6, 2, 6, 2, 4, 6, 2, 3, 2, 0, 3, 0, 6, 3, 2, 4, 5, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 6, 5, 3, 1, 2, 0, 0, 2, 0, 0, 0, 3, 3, 1, 2, 3, 0, 5, 3, 5, 4, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { 0, 1, 5, 4, 1, 6, 4, 3, 6, 4, 1, 1, 3, 4, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };
        int[] wins = { 1, 2, 1, 2, 4, 2, 1, 3, 2, 1 };
        int error = 0;

        for (int i = 0; i < plays.length; i++) {
            int winNum = 0;
            Board board = new Board(6, 7);
            Player p1 = new Player("Clive", 'o');
            Player p2 = new Player("Sharon", 'x');
            for (int j = 0; j < plays[i].length; j++) {
                if (plays[i][j] == -1) {
                    break;
                }
                Player player = (j % 2 == 0 ? p2 : p1);
                board.add(new Counter(player), plays[i][j]);
                System.out.println("Turn " + j + ". Player " + player + ". Col: " + plays[i][j]);
                if (board.isPlayerWon()) {
                    System.out.println(board);
                    System.out.println("The winner is: " + player + " (" + player.getSymbol() + "). Win condition: "
                            + board.winCondition);

                    if (board.winCondition == "horizontal") {
                        winNum = 1;
                    } else if (board.winCondition == "vertical") {
                        winNum = 2;
                    } else if (board.winCondition == "diagonal left") {
                        winNum = 3;
                    } else if (board.winCondition == "diagonal right") {
                        winNum = 4;
                    }
                    if (winNum != wins[i]) {
                        System.out.println(winNum);
                        System.out.println(wins[i]);
                        error = 1;
                        break;
                    } else {
                        System.out.println(winNum);
                        System.out.println(wins[i]);
                        break;
                    }
                }

                if (j == plays[i].length - 1) {
                    if (winNum != wins[i]) {
                        error = 1;
                    }
                    System.out.println(board);
                }
            }
            System.out.println("__________________________\n");
        }

        if (error == 1) {
            System.out.println("\n\n\t\tERROR!!!\n\n");
        }
    }

    public static void play1(int numGames) {
        String code = "{";
        String winnums = "{";
        for (int i = 0; i < numGames; i++) {
            code += "{";
            Board board = new Board(6, 7);
            Player p1 = new Player("Clive", 'o');
            Player p2 = new Player("Sharon", 'x');
            playRandomly(board, p1, p2);
            int winNum = 0;
            if (board.winCondition == "horizontal") {
                winNum = 1;
            } else if (board.winCondition == "vertical") {
                winNum = 2;
            } else if (board.winCondition == "diagonal left") {
                winNum = 3;
            } else if (board.winCondition == "diagonal right") {
                winNum = 4;
            }

            winnums += winNum;
            for (int j = 0; j < board.turnSequence.length; j++) {

                code += board.turnSequence[j];
                if (j != board.turnSequence.length - 1) {
                    code += ", ";
                }
            }
            code += "}";
            if (i != numGames - 1) {
                code += ", ";
                winnums += ", ";
            }
        }
        code += "}";
        winnums += "}";
        // System.out.println(code);
        // System.out.println(winnums);
    }

    public static void playRandomly(Board board, Player p1, Player p2) {
        Player player = p1;
        Boolean firstPlayer = true;
        String message = "It's a draw!";

        // int turns = 30;
        int i = 0;

        while (!board.isFull()) {

            // for (int i = 1; i <= turns; i++) {
            firstPlayer = !firstPlayer;
            player = firstPlayer ? p1 : p2;

            Random rand = new Random();
            int randomNum = rand.nextInt(7);

            board.add(new Counter(player), randomNum);
            System.out.println("Turn " + i + ". Player " + player + ". Col: " + randomNum);

            if (board.isPlayerWon()) {
                message = "The winner is: " + player + " (" + player.getSymbol() + "). Win condition: "
                        + board.winCondition;
                break;
            }
            i++;
        }

        System.out.println(board);
        System.out.println(message);
    }
}
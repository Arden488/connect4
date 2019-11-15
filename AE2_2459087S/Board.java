package connect4.AE2_2459087S;

/**
 * Board class
 */
public class Board {
    private Column[] columns; // Array of Column objects
    private int lastCounterRow; // The row of the last added counter (top - 0, bottom - numRows-1)
    private int lastCounterCol; // The column of the last added counter (left - 0)
    private int turn = 0; // Turns counter
    private int numRows; // Number of board' rows
    public String winCondition; // String containing explanation of the winning condition

    private int winCounterNum = 4; // Number of counters for winning sequence

    // Variables for directions checks
    // If variable is false - skip
    private Boolean isTopLeft = true;
    private Boolean isTopRight = true;
    private Boolean isBottomLeft = true;
    private Boolean isBottomRight = true;
    private Boolean isLeft = true;
    private Boolean isRight = true;
    private Boolean isBottom = true;

    // Counters of the winning sequence
    // for different directions
    private int leftDiagSeq;
    private int rightDiagSeq;
    private int vertSeq;
    private int horSeq;

    /**
     * Constructor Creates a new Board
     * 
     * @param numRows numbers of rows
     * @param numCols numbers of cols
     */
    public Board(int numRows, int numCols) {
        // Rows are assigned to a private variable
        this.numRows = numRows;

        // Columns array are sized to the numbers of cols.
        columns = new Column[numCols];

        // Every column are sized to the number of rows
        for (int i = 0; i < numCols; i++) {
            columns[i] = new Column(numRows);
        }
    }

    /**
     * Adds a counter to the top of the Column
     * 
     * @param c   counter to add
     * @param col destination column
     * @return true if added, false if not
     */
    public Boolean add(Counter c, int col) {
        Column targetCol = columns[col];

        if (targetCol.add(c)) {
            // Increment turn counter
            turn++;

            lastCounterRow = columns[col].getLastFilledRowNum(); // Find the row position
            lastCounterCol = col;

            return true;
        }

        return false;
    }

    /**
     * Display the board's graphical representation
     */
    public String toString() {
        String output = "";

        // Create board header (with column numbers)
        for (int i = 0; i < columns.length; i++) {
            output += "|" + i;
        }

        output += "\n";
        output += "---------------\n";

        // Create board body
        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < columns.length; i++) {
                // For each column and each row - output
                // string with either counter symbol or
                // space (empty)
                output += "|" + columns[i].displayRow(j);
            }
            output += "|\n";
        }

        return output;
    }

    /**
     * Checks if the Board is full (every column is full) If any column is not full
     * - then the board is not full
     * 
     * @return true if full, false if not
     */
    public Boolean isFull() {
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].isFull()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Resets internal counters to initial state
     */
    private void resetCounters() {
        leftDiagSeq = 1;
        rightDiagSeq = 1;
        vertSeq = 1;
        horSeq = 1;
        isTopLeft = true;
        isTopRight = true;
        isBottomLeft = true;
        isBottomRight = true;
        isLeft = true;
        isRight = true;
        isBottom = true;
    }

    /**
     * Checks if the last played player is winner. This method uses internal
     * counters to avoid unnecessary checks. Firstly, it resets counters. Then it
     * gets the last played counter object, which will then be checked for equality
     * with neighbour counters. Then it starts the loop with maximum of Z cycles,
     * where Z is the winning number of counters in a sequence minus 1 (because we
     * already know the counter from which we started to count). It checks
     * diagonally (left top, right top, left bottom, right bottom) and directly
     * (horizontal, vertical). Every iteration of the loop it checks if there are
     * any possible directions to check. If not - it leaves the loop and return
     * false (no winner). If any of the winning counters reach the number of the
     * winning sequence - then it returns true (current player is winner) and leaves
     * the method This algorithm is at least x10 efficient than simple loop over all
     * positions.
     * 
     * @return
     */
    public Boolean isPlayerWon() {
        resetCounters();

        // Variables to check if we can skip some checks
        Boolean diagonalCheck = true;
        Boolean directCheck = true;

        // If the total number of turns is less than
        // the number of winning sequence minus 1 -
        // then we can leave immediately and return
        // false (no winner)
        if (turn < (winCounterNum * 2 - 1)) {
            return false;
        }

        // Get current counter from which we start to check
        Counter counterToCheck = columns[lastCounterCol].getCounter(lastCounterRow);

        for (int i = 1; i <= (winCounterNum - 1); i++) {
            // Perform checks and assign the result of the check to the variables
            // If the check is completely unsuccessful (no occurences of the counter
            // in any checked direction) - assign false to variable and
            // skip it the next iteration
            diagonalCheck = diagonalCheck && isAnyDiagonalLinesWin(i, counterToCheck);
            directCheck = directCheck && isAnyDirectLinesWin(i, counterToCheck);

            // If both variables are false - we don't have
            // anything to check
            if (!diagonalCheck && !directCheck) {
                break;
            }

            // If we reach the winning sequence - return true and
            // celebrate the winner
            if (countWinSequence()) {
                winCondition = _setWinCondition();
                return true;
            }
        }

        return false;
    }

    /**
     * Utility to show winning condition
     * 
     * @return string with winning condition
     */
    private String _setWinCondition() {
        String condition = "";
        if (leftDiagSeq == winCounterNum)
            condition += "diagonal left";
        if (rightDiagSeq == winCounterNum)
            condition += "diagonal right";
        if (vertSeq == winCounterNum)
            condition += "vertical";
        if (horSeq == winCounterNum)
            condition += "horizontal";

        return condition;
    }

    /**
     * Compares every direction's counter to the number of the winning sequence
     * 
     * @return true if any sequence reached the target, false if not
     */
    private Boolean countWinSequence() {
        if (leftDiagSeq == winCounterNum || rightDiagSeq == winCounterNum || vertSeq == winCounterNum
                || horSeq == winCounterNum) {
            return true;
        }

        return false;
    }

    /**
     * Checks diagonal lines (4 directions)
     * 
     * @param i              Iteration counter, it is used to adjust the offset of
     *                       the check
     * @param counterToCheck The Counter to check against
     * @return true is any of the directions is equal to the target Counter, false
     *         if none of them matches
     */
    private Boolean isAnyDiagonalLinesWin(int i, Counter counterToCheck) {
        isTopLeft = isTopLeft && checkDiagonal(false, true, i, counterToCheck);
        isTopRight = isTopRight && checkDiagonal(false, false, i, counterToCheck);
        isBottomLeft = isBottomLeft && checkDiagonal(true, true, i, counterToCheck);
        isBottomRight = isBottomRight && checkDiagonal(true, false, i, counterToCheck);

        return isTopLeft || isTopRight || isBottomLeft || isBottomRight;
    }

    /**
     * Check if diagonal check is reaching the edge (or have minimal sensible number
     * of possible steps). Verifies if making "offset" number of steps to the left
     * and to the bottom will still be in the proper range Maximum number of columns
     * - columns.length (size of columns array), minimum - 0 Maximum number of rows
     * - numRows, minimum 0
     * 
     * @param isBottomDirection true of we look to the bottom
     * @param isLeftDirection   true if we look to the left
     * @param offset            how many steps to check
     * @return
     */
    private Boolean isWithinDiagRange(Boolean isBottomDirection, Boolean isLeftDirection, int offset) {
        if (isLeftDirection) {
            if (isBottomDirection) {
                // If its left and bottom direction
                if (lastCounterCol - offset < 0 || lastCounterRow + offset >= numRows) {
                    return false;
                }
            }

            // If its left and top direction
            if (lastCounterCol - offset < 0 || lastCounterRow - offset < 0) {
                return false;
            }
        } else {
            if (isBottomDirection) {
                // If its right and bottom direction
                if (lastCounterCol + offset >= columns.length || lastCounterRow + offset >= numRows) {
                    return false;
                }
            }

            // If its right and top direction
            if (lastCounterCol + offset >= columns.length || lastCounterRow - offset < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the target counter is of the same player as the counter located at
     * given column and row
     * 
     * @param col           Column position of the current counter
     * @param row           Row position of the current counter
     * @param targetCounter Counter to check against
     * @return true if counters belong to the same player, false if not
     */
    private Boolean isSameCounter(int col, int row, Counter targetCounter) {
        Counter counter = columns[col].getCounter(row);
        if (counter == null || !counter.equals(targetCounter)) {
            return false;
        }

        return true;
    }

    /**
     * Check one diagonal direction. It takes position of the current counter and
     * gets the position with given offset. If the counter belongs to the same
     * player, then increment the corresponding winning sequence
     * 
     * @param isBottomDirection true if it checks the bottom direction
     * @param isLeftDirection   true if it checks the left direction
     * @param offset            how many steps to make before check
     * @param targetCounter     The Counter to check against
     * @return
     */
    private Boolean checkDiagonal(Boolean isBottomDirection, Boolean isLeftDirection, int offset,
            Counter targetCounter) {
        // Verify if the check is in meaningful range
        if (!isWithinDiagRange(isBottomDirection, isLeftDirection, offset)) {
            return false;
        }

        // Calculate column and row to check depending on the direction
        int col = lastCounterCol + offset;
        int row = lastCounterRow - offset;
        if (isLeftDirection) {
            col = lastCounterCol - offset;
        }
        if (isBottomDirection) {
            row = lastCounterRow + offset;
        }

        // If the counter is not from the same player -
        // leave and return false
        if (!isSameCounter(col, row, targetCounter)) {
            return false;
        }

        // If it is from the same player - increment
        // the counter depending on the direction
        if (isLeftDirection) {
            if (isBottomDirection) {
                rightDiagSeq++;
            } else {
                leftDiagSeq++;
            }
        } else {
            if (isBottomDirection) {
                leftDiagSeq++;
            } else {
                rightDiagSeq++;
            }
        }

        return true;

    }

    /**
     * Checks direct lines (3 directions). The top straight direction shouldn't be
     * checked because the last added counter in the column will be at the top of
     * the column
     * 
     * @param i              Iteration counter, it is used to adjust the offset of
     *                       the check
     * @param counterToCheck The Counter to check against
     * @return true is any of the directions is equal to the target Counter, false
     *         if none of them matches
     */
    private Boolean isAnyDirectLinesWin(int i, Counter counterToCheck) {
        isLeft = isLeft && checkRow(true, i, counterToCheck);
        isRight = isRight && checkRow(false, i, counterToCheck);
        isBottom = isBottom && checkColumn(i, counterToCheck);

        return isLeft || isRight || isBottom;
    }

    /**
     * Check if row check is reaching the edge (or have minimal sensible number of
     * possible steps). Verifies if making "offset" number of steps to the left and
     * to the bottom will still be in the proper range. Maximum number of columns -
     * columns.length (size of columns array), minimum - 0
     * 
     * @param isLeftDirection true if we look to the left
     * @param offset          how many steps to check
     */
    private Boolean isWithinDirectRange(Boolean isLeftDirection, int offset) {
        if (isLeftDirection) {
            // if it's left direction
            if (lastCounterCol - offset < 0) {
                return false;
            }
        } else {
            // if it's right direction
            if (lastCounterCol + offset >= columns.length) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check left or right direction. It takes position of the current counter and
     * gets the position with given offset. If the counter belongs to the same
     * player, then increment the corresponding winning sequence
     * 
     * @param isLeftDirection true if it checks the left direction
     * @param offset          how many steps to make before check
     * @param targetCounter   The Counter to check against
     * @return
     */
    private Boolean checkRow(Boolean isLeftDirection, int offset, Counter targetCounter) {
        // Verify if the check is in meaningful range
        if (!isWithinDirectRange(isLeftDirection, offset)) {
            return false;
        }

        // Calculate column to check depending on the direction
        int col = lastCounterCol + offset;
        if (isLeftDirection) {
            col = lastCounterCol - offset;
        }

        // If the counter is not from the same player -
        // leave and return false
        if (!isSameCounter(col, lastCounterRow, targetCounter)) {
            return false;
        }

        // If it is from the same player - increment the sequence counter
        horSeq++;

        return true;
    }

    private Boolean checkColumn(int offset, Counter targetCounter) {
        if (lastCounterRow + winCounterNum > numRows) {
            return false;
        }

        // Calculate the row to check depending on the direction
        int row = lastCounterRow + offset;

        if (!isSameCounter(lastCounterCol, row, targetCounter)) {
            return false;
        }

        // If it is from the same player - increment the sequence counter
        vertSeq++;

        return true;
    }
}
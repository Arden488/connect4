public class Board {
    private Column[] columns;
    private int lastCounterRow;
    private int lastCounterCol;
    private int turn = 0;
    private int numRows;
    public String winCondition;
    private int winCounterNum = 4;

    public int[] turnSequence = new int[100];

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        columns = new Column[numCols];

        for (int i = 0; i < 100; i++) {
            turnSequence[i] = -1;
        }

        for (int i = 0; i < numCols; i++) {
            columns[i] = new Column(numRows);
        }
    }

    public Boolean add(Counter c, int col) {

        if (columns[col].add(c)) {
            turnSequence[turn] = col;
            turn++;

            lastCounterRow = columns[col].getLastFilledRowNum();
            lastCounterCol = col;

            return true;
        }

        return false;
    }

    public static int[] addToArray(int[] myArray, int a) {
        int pos = myArray.length;
        if (pos >= myArray.length) {
            // array needs to be made bigger
            int currentLength = myArray.length;
            int newLength = currentLength + 1;
            int[] newArray = new int[newLength];
            // copy into new one
            for (int i = 0; i < currentLength; i++) {
                newArray[i] = myArray[i];
            }
            // reset the reference
            myArray = newArray;
        }
        myArray[pos++] = a;

        return myArray;
    }

    public String toString() {
        String output = "";

        for (int i = 0; i < columns.length; i++) {
            output += "|" + i;
        }
        output += "\n";
        output += "---------------\n";

        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < columns.length; i++) {
                output += "|" + columns[i].displayRow(j);
            }
            output += "|\n";
        }

        return output;
    }

    public Boolean isFull() {
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].isFull()) {
                return false;
            }
        }

        return true;
    }

    public Boolean isPlayerWon() {
        // System.out.println(this.toString());

        if (turn < (winCounterNum * 2 - 1)) {
            return false;
        }

        Counter counterToCheck = columns[lastCounterCol].getCounter(lastCounterRow);

        if (isAnyDiagonalLinesWin(counterToCheck) || isAnyDirectLinesWin(counterToCheck)) {
            return true;
        }

        return false;
    }

    public Boolean isAnyDiagonalLinesWin(Counter counterToCheck) {
        Boolean isTopLeft = true;
        Boolean isTopRight = true;
        Boolean isBottomLeft = true;
        Boolean isBottomRight = true;
        int leftSequence = 1;
        int rightSequence = 1;

        for (int i = 1; i <= (winCounterNum - 1); i++) {

            if (isTopLeft) {
                if (checkDiagonal(false, true, i, counterToCheck)) {
                    leftSequence++;
                } else {
                    isTopLeft = false;
                }
            }

            if (isTopRight) {
                if (checkDiagonal(false, false, i, counterToCheck)) {
                    rightSequence++;
                } else {
                    isTopRight = false;
                }
            }

            if (isBottomLeft) {
                if (checkDiagonal(true, true, i, counterToCheck)) {
                    rightSequence++;
                } else {
                    isBottomLeft = false;
                }
            }

            if (isBottomRight) {
                if (checkDiagonal(true, false, i, counterToCheck)) {
                    leftSequence++;
                } else {
                    isBottomRight = false;
                }
            }

            if ((!isTopLeft && !isTopRight && !isBottomLeft && !isBottomRight) || leftSequence == winCounterNum
                    || rightSequence == winCounterNum) {
                break;
            }
        }

        if (leftSequence == winCounterNum) {
            winCondition = "diagonal left";
            return true;
        }

        if (rightSequence == winCounterNum) {
            winCondition = "diagonal right";
            return true;
        }

        return false;
    }

    public Boolean checkDiagonal(Boolean isBottomDirection, Boolean isLeftDirection, int offset,
            Counter targetCounter) {
        if (isLeftDirection) {
            if (isBottomDirection) {
                if (lastCounterCol - offset < 0 || lastCounterRow + offset >= numRows) {
                    return false;
                }
            }

            if (lastCounterCol - offset < 0 || lastCounterRow - offset < 0) {
                return false;
            }
        } else {
            if (isBottomDirection) {
                if (lastCounterCol + offset >= columns.length || lastCounterRow + offset >= numRows) {
                    return false;
                }
            }

            if (lastCounterCol + offset >= columns.length || lastCounterRow - offset < 0) {
                return false;
            }
        }

        int col = lastCounterCol + offset;
        int row = lastCounterRow - offset;

        if (isLeftDirection) {
            col = lastCounterCol - offset;
        }

        if (isBottomDirection) {
            row = lastCounterRow + offset;
        }

        Counter counter = columns[col].getCounter(row);

        if (counter == null || !counter.equals(targetCounter)) {
            return false;
        }

        return true;

    }

    public Boolean isAnyDirectLinesWin(Counter counterToCheck) {
        Boolean isLeft = true;
        Boolean isRight = true;
        Boolean isBottom = true;
        int rowSequence = 1;
        int colSequence = 1;

        for (int i = 1; i <= (winCounterNum - 1); i++) {
            if (isLeft) {
                if (checkRow(true, i, counterToCheck)) {
                    rowSequence++;
                } else {
                    isLeft = false;
                }
            }

            if (isRight) {
                if (checkRow(false, i, counterToCheck)) {
                    rowSequence++;
                } else {
                    isRight = false;
                }
            }

            if (isBottom) {
                if (checkColumn(i, counterToCheck)) {
                    colSequence++;
                } else {
                    isBottom = false;
                }
            }

            if ((!isLeft && !isRight && !isBottom) || rowSequence == winCounterNum || colSequence == winCounterNum) {
                break;
            }
        }

        if (rowSequence == winCounterNum) {
            winCondition = "horizontal";
            return true;
        }

        if (colSequence == winCounterNum) {
            winCondition = "vertical";
            return true;
        }

        return false;
    }

    public Boolean checkRow(Boolean isLeftDirection, int offset, Counter targetCounter) {
        if (isLeftDirection) {
            if (lastCounterCol - offset < 0) {
                return false;
            }
        } else {
            if (lastCounterCol + offset >= columns.length) {
                return false;
            }
        }

        int col = lastCounterCol + offset;

        if (isLeftDirection) {
            col = lastCounterCol - offset;
        }

        Counter counter = columns[col].getCounter(lastCounterRow);
        if (counter == null || !counter.equals(targetCounter)) {
            return false;
        }

        return true;
    }

    public Boolean checkColumn(int offset, Counter targetCounter) {
        if (lastCounterRow + winCounterNum > numRows) {
            return false;
        }

        Counter bottomCounter = columns[lastCounterCol].getCounter(lastCounterRow + offset);
        if (bottomCounter == null || !bottomCounter.equals(targetCounter)) {
            return false;
        }

        return true;
    }
}
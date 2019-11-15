package connect4;

public class Column {
    private int numRows;
    private Counter[] rows;
    private int counterPos = 0;

    public Column(int numRows) {
        this.numRows = numRows;
        counterPos = numRows;
        rows = new Counter[numRows];
    }

    public boolean isFull() {
        if (counterPos == 0)
            return true;

        return false;
    }

    public boolean add(Counter c) {
        if (isFull())
            return false;

        rows[counterPos - 1] = c;
        counterPos--;

        return true;
    }

    public Counter getCounter(int pos) {
        return rows[pos];
    }

    public int getLastFilledRowNum() {
        return counterPos;
    }

    public String displayRow(int num) {
        if (rows[num] == null)
            return " ";

        Counter row = rows[num];
        return row.toString();
    }

    public String display() {
        String output = "";

        for (int i = 0; i < numRows; i++) {
            output += displayRow(i);
        }

        return output;
    }
}
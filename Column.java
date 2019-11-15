package connect4;

/**
 * Column class
 */
public class Column {
    private int numRows; // number of rows in column
    private Counter[] rows; // Array of counters
    private int counterPos = 0; // last counter position

    /**
     * Constructor
     */
    public Column(int numRows) {
        this.numRows = numRows;
        counterPos = numRows; // Initial position is the bottom of the column (top - 0)
        rows = new Counter[numRows]; // Resize the array to fit correct number of counters
    }

    /**
     * Checks if the column is full. If counter position is 0 then column is full
     * 
     * @return true if is full, false if not
     */
    public boolean isFull() {
        if (counterPos == 0)
            return true;

        return false;
    }

    /**
     * Adds the counter to the column
     */
    public boolean add(Counter c) {
        // If column is full - return false
        if (isFull())
            return false;

        // Add the counter to the next position
        rows[counterPos - 1] = c;

        // Update the last counter position
        counterPos--;

        return true;
    }

    /**
     * Gets counter from the given position
     * 
     * @param pos position of the counter
     * @return counter or null
     */
    public Counter getCounter(int pos) {
        return rows[pos];
    }

    /**
     * Return the last counter position
     * 
     * @return
     */
    public int getLastFilledRowNum() {
        return counterPos;
    }

    /**
     * Displays the current counter symbol or space if it's empty
     * 
     * @param num
     * @return
     */
    public String displayRow(int num) {
        if (rows[num] == null)
            return " ";

        Counter row = rows[num];
        return row.toString();
    }

    /**
     * Utility method to display the whole column
     * 
     * @return
     */
    public String display() {
        String output = "";

        for (int i = 0; i < numRows; i++) {
            output += displayRow(i);
        }

        return output;
    }
}
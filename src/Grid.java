public class Grid {

    private int[][] gameGrid;
    private int n;

    // A constructor that creates an empty n × n grid
    public Grid(int n) {

        this.n = n;
        gameGrid = new int[n][n];


        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                gameGrid[row][column] = 0;
            }
        }
    }

    // Returns the value at the given cell
    public int getCell(int row, int col) {
        return gameGrid[row][col];
    }

    // Store the given value in the designated cell
    public void setCell(int row, int col, int val) {
        gameGrid[row][col] = val;

    }

    // Return true if the designated cell is not empty
    public Boolean isSet(int row, int col) {
        return gameGrid[row][col] != 0;
    }

    //Set all cell values back to 0
    public void clear() {

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                gameGrid[row][column] = 0;
            }
        }

    }
}

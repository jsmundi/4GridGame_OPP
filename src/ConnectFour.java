import java.util.Random;

public class ConnectFour extends GridGame {

    // ANSI valeus for colors
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private Grid newGame; // Game board
    private int gridN = 4; // Grid size

    @Override
    public void initializeBoard() {
        newGame = new Grid(gridN);
    }

    @Override
    public int checkCol() {

        for (int i = 0; i < gridN; i++) {
            if ((newGame.getCell(0, i) == 1 && newGame.getCell(1, i) == 1 && newGame.getCell(2, i) == 1
                    && newGame.getCell(3, i) == 1)) {
                return 1;

            } else if ((newGame.getCell(0, i) == 2 && newGame.getCell(1, i) == 2 && newGame.getCell(2, i) == 2
                    && newGame.getCell(3, i) == 2)) {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public int checkRow() {

        for (int i = 0; i < gridN; i++) {
            if ((newGame.getCell(i, 0) == 1 && newGame.getCell(i, 1) == 1 && newGame.getCell(i, 2) == 1)
                    && newGame.getCell(i, 3) == 1) {
                return 1;

            } else if ((newGame.getCell(i, 0) == 2 && newGame.getCell(i, 1) == 2 && newGame.getCell(i, 2) == 2
                    && newGame.getCell(i, 3) == 2)) {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public int checkDig() {
        if ((newGame.getCell(0, 0) == 1 && newGame.getCell(1, 1) == 1 && newGame.getCell(2, 2) == 1
                && newGame.getCell(3, 3) == 1)
                || (newGame.getCell(0, 3) == 1 && newGame.getCell(1, 2) == 1 && newGame.getCell(2, 1) == 1
                && newGame.getCell(3, 0) == 1)) {
            return 1;
        } else if ((newGame.getCell(0, 0) == 2 && newGame.getCell(1, 1) == 2 && newGame.getCell(2, 2) == 2
                && newGame.getCell(3, 3) == 2)
                || (newGame.getCell(0, 3) == 2 && newGame.getCell(1, 2) == 2 && newGame.getCell(2, 1) == 2
                && newGame.getCell(3, 0) == 2)) {
            return 2;
        }
        return 0;
    }

    @Override
    public Boolean isDraw() {

        for (int row = 0; row < gridN; row++) {
            for (int column = 0; column < gridN; column++) {
                if (!newGame.isSet(row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getStatus() {
        if (checkCol() == 1 || checkRow() == 1 || checkDig() == 1) {
            return 1;
        } else if (checkCol() == 2 || checkRow() == 2 || checkDig() == 2) {
            return 2;
        } else {
            if (isDraw()) {
                return 3;
            }
        }
        return 0;
    }

    @Override
    public Boolean placeMark(int row, int col, int player) {

        if ((row >= 0) && (row < gridN)) {
            if ((col >= 0) && (col < gridN)) {
                if (!newGame.isSet(row, col)) {
                    newGame.setCell(row, col, player);
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * The algorithm try's to make a move to win first. If not possible the board is
     * set to original state. Then the algorithm try's to make a move for the user
     * to win. If not possible the board is reset to original state and a random
     * move is placed on the board where space is available.
     */
    @Override
    public void placeMark(int player) {

        int temp;

        if (player == 1) {
            temp = 2;
        } else {
            temp = 1;
        }

        for (int row = 0; row < gridN; row++) {
            for (int column = 0; column < gridN; column++) {

                if (newGame.getCell(row, column) == 0) {

                    newGame.setCell(row, column, player);
                    if (getStatus() == 1 || getStatus() == 2 || getStatus() == 3) {
                        newGame.setCell(row, column, 0);
                        placeMark(row, column, player);
                        return;
                    } else {
                        newGame.setCell(row, column, 0);
                    }

                    newGame.setCell(row, column, temp);
                    if (getStatus() == 1 || getStatus() == 2 || getStatus() == 3) {
                        newGame.setCell(row, column, 0);
                        placeMark(row, column, player);
                        return;
                    } else {
                        newGame.setCell(row, column, 0);
                    }
                }
            }
        }

        while (true) {
            Random rand = new Random();
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);

            if (newGame.getCell(x, y) == 0) { // Check if spot is empty
                placeMark(x, y, player);
                return;
            }
        }

    }

    @Override
    public void printGrid() {
        System.out.println("----------------");
        for (int row = 0; row < gridN; row++) {
            System.out.print("|");

            for (int column = 0; column < gridN; column++) {
                if (newGame.getCell(row, column) == 1) {
                    System.out.print(ANSI_BLUE + " B " + ANSI_RESET);
                } else if (newGame.getCell(row, column) == 2) {
                    System.out.print(ANSI_YELLOW + " Y " + ANSI_RESET);
                } else if (newGame.getCell(row, column) == 0) {
                    System.out.print(" - ");
                }
                System.out.print("|");

            }
            System.out.print("\n");

        }
        System.out.println("----------------");
    }

    @Override
    public void restart() {
        newGame.clear();
    }
}

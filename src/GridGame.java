
public abstract class GridGame {

	Grid gridGame;

	/*
	 * Initialize all the the board rows and columns with zeros.
	 */
	public abstract void initializeBoard();

	/*
	 * Loop the game board columns to check for a win for user or computer.
	 */
	public abstract int checkCol();

	/*
	 * Loop the game board rows to check for a win for user or computer.
	 */
	public abstract int checkRow();

	/*
	 * Loop the game board diagonals to check for a win for user or computer.
	 */
	public abstract int checkDig();

	/*
	 * Get the win status for user or computer or a tie. Return 1 or 2 for win
	 * status and 3 for a tie
	 */
	public abstract int getStatus();

	/*
	 * Place a player mark on the board at given row and column return true if mark
	 * placed.
	 */
	public abstract Boolean placeMark(int row, int col, int player);

	/*
	 * Place the mark for provided player automatically by running a custom
	 * algorithm.
	 */
	public abstract void placeMark(int player);

	/*
	 * Print the grid with current player marks.
	 */
	public abstract void printGrid();

	/*
	 * Clear the game board
	 */
	public abstract void restart();

	/*
	 * Check the game status for a draw. Returns true if all the cells are filled
	 * and no more cells are available.
	 */
	public abstract Boolean isDraw();
}

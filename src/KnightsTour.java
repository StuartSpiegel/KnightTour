
public class KnightsTour {

	private static final int boardSize = 8;
	private int[][] theBoard;

	public KnightsTour() {
		theBoard = new int[boardSize][boardSize];
	}

	/**
	 * Utility to check if the current square is valid
	 * 
	 * @param x
	 *            : the x coordinate of the square being checked
	 * @param y
	 *            : the y coordinate of the square being checked
	 * @return true if the square is valid, otherwise return false
	 */
	public boolean isSafe(int x, int y) {

		if (x >= 0 && x < boardSize && y >= 0 && y < boardSize && theBoard[x][y] == -1) {
			return true;
		}
		return false;

	}

	/**
	 * function that outputs the board to the console
	 */
	public void printBoard() {

		for (int k = 0; k < boardSize; k++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(" " + theBoard[k][j]);
			}
			System.out.println();
		}

	}

	/**
	 * The Algorithm used in this utility function is DFS (Depth First Search)
	 * 
	 * @param x
	 *            is the initial x coordinate of the knight (start x location)
	 * @param y
	 *            is the initial y coordinate of the knight (start y location)
	 * @param move
	 *            is the location of the next move after it is checked for validity
	 * @param xMoves
	 *            storage for x values already visited
	 * @param yMoves
	 *            storage for y values already visited
	 * @return returns true if a solution exists, otherwise false
	 */
	public boolean solveKnightTour(int x, int y, int move, int xMoves[], int yMoves[]) {

		total++;
		if(total%10000 ==0) {
			System.out.println(total+ " " + move);
		}
		int nextX = 0;
		int nextY = 0;

		if (move == boardSize * boardSize ) {
			return true;
		}

		// gets the position for the next move
		for (int k = 0; k < xMoves.length; k++) {

			nextX = x + xMoves[k];
			nextY = y + yMoves[k];

			if (isSafe(nextX, nextY)) {

				theBoard[nextX][nextY] = move; // if its safe then move the knight

				if (solveKnightTour(nextX, nextY, move + 1, xMoves, yMoves)) {
					return true; // looks ahead to the next valid square with recursive call
				} else {
					theBoard[nextX][nextY] = -1; // already been visited
				}
			}
		}
		return false; // dummy return
	}

	/*
	 * Sets all starting conditions for the problem and then utilizes the
	 * SolveKnightTour function to output a solution to the problem
	 * 
	 */
	public boolean Solve() {

		for (int k = 0; k < boardSize; k++) {
			for (int j = 0; j < boardSize; j++) {
				theBoard[k][j] = -1; // all squares have not yet been visited, they are changed when the knight
				// visits the square
			}
		}

		theBoard[0][0] = 0; // set starting position of the knight

		int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; // holds x coordinates of moving patterns
		int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; // holds y coordinates of moving patterns

		// calls the algorithm to determine if move series is valid
		if (!solveKnightTour(0, 0, 1, xMove, yMove)) {
			System.out.println("No solution exists");
			return false; // solution does not exists
		} else {
			printBoard();
		}
		return true;

	}

	static int total = 0;
	public static void main(String[] args) {
		KnightsTour KnightTour = new KnightsTour();
		System.out.print("Solution: ");
		System.out.println();
		KnightTour.Solve();
		System.out.println(total);
	}

}

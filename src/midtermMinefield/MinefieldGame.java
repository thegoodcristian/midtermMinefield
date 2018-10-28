package midtermMinefield;

public class MinefieldGame {

	static int userDifficulty = 0;
	private String mine = " * ";
	private String unPressed = " . ";
	private String flagged = " F ";
	private int row = 0;
	private int column = 0;

	// public String[][] getTile(int column, int row) {
	// return [column][row];
	// }

	String[][] board = new String[10][10];

	public void createBoard() {

		// runs along every cell of the board

		while (row <= board.length - 1) {

			for (column = 0; column < board.length; column++) {

				board[column][row] = unPressed;
				System.out.print(board[column][row]);

			}
			row++;
			System.out.println("");
		}

	}

	public void placeMines(int numMines) {
		for (int m = 0; m < numMines; m++) {
			// loop continues until space is mined
			while (true) {
				int column, row = 0; // reset rows and columns to 0
				column = (int) (10 * Math.random());
				row = (int) (10 * Math.random());

				// checks if a mine is present in a spot.
				if (!(board[column][row].equalsIgnoreCase(" * "))) {
					board[column][row] = " * ";
					break;
				}

			}
		}
	}

	public void updateBoard(int userCol, int userRow, String userOption) {

		column = 0;
		row = 0;

		// runs along every cell of the board
		if (userOption.equalsIgnoreCase("FLAG")) {

			board[userCol - 1][userRow - 1] = flagged;

			while (row <= board.length - 1) {

				for (column = 0; column < board.length; column++) {

					System.out.print(board[column][row]);

				}
				row++;
				System.out.println("");
			}

		} else if (userOption.equalsIgnoreCase("UNCOVER")) {

			if (board[userCol - 1][userRow - 1] == " * ") {
				System.out.println("GAME OVER");
			} else if (!(board[userCol - 1][userRow - 1] == " * ")) {

				senseMines(userCol, userRow);
				System.out.println(board[userCol][userRow]);
				// TODO: Insert senseMines method

				
				while (row <= board.length - 1) {

					for (column = 0; column < board.length; column++) {

						System.out.print(board[column][row]);

					}
					row++;
					System.out.println("");
			}



			}

		} else {
			System.out.println("ERROR");
		}

	}

	int mineCounter = 0; // counter variable

	public void senseMines(int column, int row) {

		for (int i = (column - 2); i <= (column); i++) {
			for (int j = (row - 2); j <= (row); j++) {
				if ((board[i][j].equalsIgnoreCase(" * ")) == true)
					mineCounter++; // when mine is sensed, counter increases
			}
		}
		board[column-1][row-1] = " " + Integer.toString(mineCounter) + " ";


//
//			public void senseMines() {
//				for (int column = 1; column < overlay.length - 2; column++) { // checks entire array
//					for (int row = 1; row < overlay.length - 2; row++) {
//						if ((board[column][row] == getBlank()) == true) {
//							mineCounter = 0; // counter variable
//							for (int i = (column - 1); i <= (column + 1); i++) {
//								for (int j = (row - 1); j <= (row + 1); j++) {
//									if ((board[i][j] == getMine()) == true)
//										mineCounter++; // when mine is sensed, counter increases
//								}
//							}
//							overlay[column][row] = (char) getNumOfMines();
//						}
//					}
//				}
//			}

	}
}

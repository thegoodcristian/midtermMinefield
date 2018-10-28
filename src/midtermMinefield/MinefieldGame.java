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

	String[][] board = new String[12][12];
	String[][] overlay = new String[12][12];

	public void createBoard() {

		// runs along every cell of the board
		
		
		

		while (row <= board.length - 1) {

			for (column = 0; column < board.length; column++) {

				board[column][row] = unPressed;
				board[column][0] = " ";
				board[0][row] = " ";
				board[column][11] = " ";
				board[11][row] = " ";
				System.out.print(board[column][row]);
				
			}
			row++;
			System.out.println("");
		}

	}

//	public void createOverlay() {
//
//		// runs along every cell of the board
//
//		while (row <= board.length - 1) {
//
//			for (column = 0; column < board.length; column++) {
//
//				board[column][row] = unPressed;
//				System.out.print(board[column][row]);
//
//			}
//			row++;
//			System.out.println("");
//		}
//
//	}

	public void placeMines(int numMines) {
		for (int m = 0; m < numMines; m++) {
			// loop continues until space is mined
			while (true) {
				int column, row = 0; // reset rows and columns to 0
				column = (int) (10 * Math.random());
				row = (int) (10 * Math.random());

				// excludes buffer zones
				if (column >= 1 && column <= 10) {
					if (row >= 1 && row <= 10) {

						// checks if a mine is present in a spot.
						if (!(board[column][row].equalsIgnoreCase(" * "))) {
							board[column][row] = " * ";
							break;
						}
					}
				}
			}
		}
	}

	public void updateBoard(int userCol, int userRow, String userOption) {

		column = 0;
		row = 0;

		// runs along every cell of the board
		if (userOption.equalsIgnoreCase("FLAG")) {

			board[userCol][userRow] = flagged;
			overlay[userCol][userRow] = flagged;

			while (row <= board.length - 1) {

				for (column = 0; column < board.length; column++) {

					System.out.print(board[column][row]);

				}
				row++;
				System.out.println("");
			}

		} else if (userOption.equalsIgnoreCase("UNCOVER")) {

			if (board[userCol][userRow] == " * ") {
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

				if (board[userCol][userRow] == " 0 ") {

					senseMines(userCol, userRow);

					while (row <= board.length - 1) {

						for (column = 0; column < board.length; column++) {

							System.out.print(board[column][row]);

						}
						row++;
						System.out.println("");
					}

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
		board[column][row] = " " + Integer.toString(mineCounter) + " ";

//		if (board[column -1][row - 1].equalsIgnoreCase(" 0 ") && (row -1 < board.length)) {
//			row++;
//			senseMines(column, row);
//		}

	}
}

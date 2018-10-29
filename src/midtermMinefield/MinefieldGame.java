package midtermMinefield;

public class MinefieldGame {

	static int userDifficulty;
	private String mine = " * ";
	private String unPressed = " . ";
	private String flagged = " F ";
	private String detonationPoint = " ! ";
	private final String blank = " ";
	private int row = 0;
	private int column = 0;
	private int overlayRow = 0;
	private int overlayColumn = 0;
	static boolean isFinished = false;

	// public String[][] getTile(int column, int row) {
	// return [column][row];
	// }

	String[][] board = new String[userDifficulty][userDifficulty];
	String[][] overlay = new String[userDifficulty][userDifficulty];

	public void createBoard() {

		// runs along every cell of the board

		while (row < board.length) {

			for (column = 0; column < board.length; column++) {

				board[column][row] = unPressed;
				board[column][0] = " ";
				board[column][1] = " ";
				board[0][row] = " ";
				board[1][row] = " ";
				board[column][board.length - 1] = " ";
				board[column][board.length - 2] = " ";
				board[board.length - 1][row] = " ";
				board[board.length - 2][row] = " ";
				// System.out.print(board[column][row]);

			}
			row++;
			// System.out.println("");
		}

	}

	public void createOverlay() {

		// runs along every cell of the board

		while (overlayRow < overlay.length) {

			for (overlayColumn = 0; overlayColumn < overlay.length; overlayColumn++) {

				overlay[overlayColumn][overlayRow] = unPressed;
				overlay[overlayColumn][0] = " ";
				overlay[overlayColumn][1] = " ";
				overlay[0][overlayRow] = " ";
				overlay[1][overlayRow] = " ";
				overlay[overlayColumn][board.length - 1] = " ";
				overlay[overlayColumn][board.length - 2] = " ";
				overlay[board.length - 1][overlayRow] = " ";
				overlay[board.length - 2][overlayRow] = " ";
				System.out.print(overlay[overlayColumn][overlayRow]);

			}
			overlayRow++;
			System.out.println("");
		}

	}

	public void placeMines(int numMines) {
		for (int m = 0; m < numMines; m++) {
			// loop continues until space is mined
			while (true) {
				int column, row = 0; // reset rows and columns to 0
				column = (int) (userDifficulty * Math.random());
				row = (int) (userDifficulty * Math.random());

				// excludes buffer zones
				if (column > 2 && column < board.length - 2) {
					if (row > 2 && row < board.length - 2) {

						// checks if a mine is present in a spot.
						if (!(board[column][row].equalsIgnoreCase(mine))) {
							board[column][row] = mine;
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

			board[userCol + 1][userRow + 1] = flagged;
			overlay[userCol + 1][userRow + 1] = flagged;

			while (row < board.length) {

				for (column = 0; column < board.length; column++) {

					System.out.print(overlay[column][row]);

				}
				row++;
				System.out.println("");
			}

		} else if (userOption.equalsIgnoreCase("UNCOVER")) {

			if (board[userCol + 1][userRow + 1] == mine) {
				System.out.println("GAME OVER. You lose :(");
				board[userCol + 1][userRow + 1] = detonationPoint;

				reassignBuffer();
				row = 2;
				while (row < board.length - 2) {

					for (column = 2; column < board.length - 2; column++) {
						System.out.print(board[column][row]);
					}
					row++;
					System.out.println("");
				}
				System.exit(0);
			} else if (!(board[userCol + 1][userRow + 1] == mine)) {

				senseMines(userCol + 1, userRow + 1);

				if (board[userCol + 1][userRow + 1].equalsIgnoreCase(" 0 ")) {

					senseMines((userCol + 1), userRow + 2);
					senseMines((userCol + 1), userRow);
					senseMines((userCol + 2), userRow + 1);
					senseMines(userCol, (userRow + 1));
					senseMines((userCol + 2), userRow);
					senseMines(userCol, (userRow + 2));
					senseMines((userCol + 2), (userRow + 2));
					senseMines(userCol, userRow);

				}
				reassignBuffer();

				while (row < board.length) {
					for (column = 0; column < board.length; column++) {

						System.out.print(overlay[column][row]);

					}
					row++;
					System.out.println("");
				}

			}

		} else {
			System.out.println("ERROR");
		}

	}

	public void reassignBuffer() {

		overlayRow = 0;
		overlayColumn = 0;
		row = 0;
		column = 0;
		
		while (overlayRow < board.length) {
			for (overlayColumn = 0; overlayColumn < board.length; overlayColumn++) {

				overlay[overlayColumn][0] = blank;
				overlay[overlayColumn][1] = blank;
				overlay[0][overlayRow] = blank;
				overlay[1][overlayRow] = blank;
				overlay[overlayColumn][board.length-1] = blank;
				overlay[overlayColumn][board.length - 2] = blank;
				overlay[board.length-1][overlayRow] = blank;
				overlay[board.length - 2][overlayRow] = blank;

				board[column][0] = blank;
				board[column][1] = blank;
				board[0][row] = blank;
				board[1][row] = blank;
				board[column][board.length-1] = blank;
				board[column][board.length - 2] = blank;
				board[board.length-1][row] = blank;
				board[board.length - 2][row] = blank;

				
			}
			overlayRow++;
			
			
			
		}
	}

	public void senseMines(int column, int row) {
		int mineCounter = 0; // counter variable

		for (int i = (column - 1); i <= (column + 1); i++) {
			for (int j = (row - 1); j <= (row + 1); j++) {
				if ((board[i][j].equalsIgnoreCase(mine)) == true)
					mineCounter++; // when mine is sensed, counter increases
			}
		}
		
		if (!(board[column][row] == flagged)) {
		
		board[column][row] = " " + Integer.toString(mineCounter) + " ";
		overlay[column][row] = " " + Integer.toString(mineCounter) + " ";
		} else { 
			return;
		}

//		if (board[column -1][row - 1].equalsIgnoreCase(" 0 ") && (row -1 < board.length)) {
//			row++;
//			senseMines(column, row);
//		}

	}

	public boolean isFinished() {
//		int unvisited = 0; // num of unvisited locations
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[0].length; j++) {
//				if (board[i][j].equals(unPressed) == true)
//					unvisited++; // increments for each un-cleared location
//			}
//		}

		int numMines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].equals(mine) == true)
					numMines++; // increments for each un-cleared location
			}
		}

		if (numMines == 0) {
			System.out.println("You win!");
			while (row < board.length) {

				for (column = 0; column < board.length; column++) {
					System.out.print(board[column][row]);
				}
				row++;
				System.out.println("");
			}
			isFinished = true;

		}
		return isFinished;

	}

}
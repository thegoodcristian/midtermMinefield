package midtermMinefield;

public class MinefieldGame {

	static int userDifficulty = 0;
	private String mine = " * ";
	private String unPressed = " . ";
	private String flagged = " F ";
	private String detonationPoint = " ! ";
	private int row = 0;
	private int column = 0;
	private int overlayRow = 0;
	private int overlayColumn = 0;
	static boolean isFinished = false;

	// public String[][] getTile(int column, int row) {
	// return [column][row];
	// }

	String[][] board = new String[12][12];
	String[][] overlay = new String[12][12];

	public void createBoard() {

		// runs along every cell of the board

		while (row < board.length) {

			for (column = 0; column < board.length; column++) {

				board[column][row] = unPressed;
				board[column][0] = " ";
				board[0][row] = " ";
				board[column][11] = " ";
				board[11][row] = " ";
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
				overlay[0][overlayRow] = " ";
				overlay[overlayColumn][11] = " ";
				overlay[11][overlayRow] = " ";
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
				column = (int) (10 * Math.random());
				row = (int) (10 * Math.random());

				// excludes buffer zones
				if (column >= 1 && column <= 10) {
					if (row >= 1 && row <= 10) {

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

			board[userCol][userRow] = flagged;
			overlay[userCol][userRow] = flagged;

			while (row < board.length) {

				for (column = 0; column < board.length; column++) {

					System.out.print(overlay[column][row]);

				}
				row++;
				System.out.println("");
			}

		} else if (userOption.equalsIgnoreCase("UNCOVER")) {

			if (board[userCol][userRow] == mine) {
				System.out.println("GAME OVER");
				board[userCol][userRow] = detonationPoint;

				while (row < board.length) {

					for (column = 0; column < board.length; column++) {
						System.out.print(board[column][row]);
					}
					row++;
					System.out.println("");
				}
			} else if (!(board[userCol][userRow] == mine)) {

				senseMines(userCol, userRow);

				if (board[userCol][userRow].equalsIgnoreCase(" 0 ")) {

					senseMines(userCol , (userRow - 1));
					senseMines(userCol , (userRow + 1));
					senseMines((userCol + 1) , userRow);
					senseMines((userCol - 1) , userRow);
					senseMines((userCol + 1) , (userRow - 1));
					senseMines((userCol - 1) , (userRow + 1));
					senseMines((userCol + 1) , (userRow + 1));
					senseMines((userCol - 1) , (userRow - 1));
				}
				
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

	public void senseMines(int column, int row) {
		int mineCounter = 0; // counter variable
		for (int i = (column - 1); i <= (column + 1); i++) {
			for (int j = (row - 1); j <= (row + 1); j++) {
				if ((board[i][j].equalsIgnoreCase(mine)) == true)
					mineCounter++; // when mine is sensed, counter increases
			}
		}
		board[column][row] = " " + Integer.toString(mineCounter) + " ";
		overlay[column][row] = " " + Integer.toString(mineCounter) + " ";

//		if (board[column -1][row - 1].equalsIgnoreCase(" 0 ") && (row -1 < board.length)) {
//			row++;
//			senseMines(column, row);
//		}

	}

	public boolean isFinished() {
		int unvisited = 0; // num of unvisited locations
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].equals(unPressed) == true)
					unvisited++; // increments for each un-cleared location
			}
		}
		
		int numMines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].equals(mine) == true)
					numMines++; // increments for each un-cleared location
			}
		}
		
		
		if (unvisited == 0 && numMines == 0) {
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

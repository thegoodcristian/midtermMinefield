package midtermMinefield;

public class MinefieldGame {

	static int userDifficulty;
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
				board[column][board.length-1] = " ";
				board[column][board.length-2] = " ";
				board[board.length-1][row] = " ";
				board[board.length-2][row] = " ";
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
				overlay[overlayColumn][board.length-1] = " ";
				overlay[overlayColumn][board.length-2] = " ";
				overlay[board.length-1][overlayRow] = " ";
				overlay[board.length-2][overlayRow] = " ";
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
				System.out.println("GAME OVER");
				board[userCol + 1][userRow + 1] = detonationPoint;

				while (row < board.length) {

					for (column = 0; column < board.length; column++) {
						System.out.print(board[column][row]);
					}
					row++;
					System.out.println("");
				}
			} else if (!(board[userCol + 1][userRow + 1] == mine)) {

				senseMines(userCol + 1, userRow + 1);

				if (board[userCol + 1][userRow + 1].equalsIgnoreCase(" 0 ")) {

					senseMines((userCol + 1) , userRow);
					senseMines((userCol + 1), userRow);
					senseMines((userCol + 2) , userRow + 1);
					senseMines(userCol , (userRow + 1));
					senseMines((userCol + 2) , userRow);
					senseMines(userCol , (userRow + 2));
					senseMines((userCol + 2) , (userRow + 2));
					senseMines(userCol , userRow);
					
					
					
					
//					int northCol = userCol;
//					int northRow = userRow;
//					int southCol = userCol;
//					int southRow = userRow;
//					int eastCol = userCol;
//					int eastRow = userRow;
//					int westCol = userCol;
//					int westRow = userRow;
//					int northWestCol = userCol;
//					int northWestRow = userRow;
//					int northEastCol = userCol;
//					int northEastRow = userRow;
//					int southWestCol = userCol;
//					int southWestRow = userRow;
//					int southEastCol = userCol;
//					int southEastRow = userRow;
//					
//					while (board[northCol][northRow].equalsIgnoreCase(" 0 ") && northRow > 1) {
//						northRow--;
//					senseMines(northCol , (northRow));
//					}
//					while (board[southCol][southRow].equalsIgnoreCase(" 0 ") && southRow < board.length - 1) {
//						southRow++;
//					senseMines(southCol , (southRow));
//					}
//					while (board[eastCol][eastRow].equalsIgnoreCase(" 0 ") && eastCol < board.length - 1) {
//						eastCol++;
//					senseMines((eastCol) , eastRow);
//					}
//					while (board[westCol][westRow].equalsIgnoreCase(" 0 ") && westCol > 1) {
//						westCol--;
//					senseMines((westCol) , westRow);
//					}
//					while (board[northEastCol][northEastRow].equalsIgnoreCase(" 0 ") && northEastCol < board.length - 1 && northEastRow > 1) {
//						northEastCol++;
//						northEastRow--;
//					senseMines((northEastCol) , (northEastRow));
//					}
//					while (board[northWestCol][northWestRow].equalsIgnoreCase(" 0 ") && northWestCol > 1 && northWestRow > 1) {
//						northWestCol--;
//						northWestRow--;
//					senseMines((northWestCol) , (northWestRow));
//					}
//					while (board[southEastCol][southEastRow].equalsIgnoreCase(" 0 ") && southEastCol < board.length - 1 && southEastRow < board.length - 1) {
//						southEastCol++;
//						southEastRow++;
//					senseMines((southEastCol) , (southEastRow));
//					}
//					while (board[southWestCol][southWestRow].equalsIgnoreCase(" 0 ") && southWestCol > 1 && southWestRow < board.length - 1) {
//						southWestCol--;
//						southWestRow++;
//					senseMines((southWestCol) , (southWestRow));
//					}
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
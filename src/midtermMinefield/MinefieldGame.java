package midtermMinefield;

//Declare class MinefieldGame
public class MinefieldGame {

	// static field to pass userDifficulty between MinefieldGame and
	// MinefieldGameApp classes
	static int userDifficulty;
	// instance variables pertaining to the various possible game tiles
	private String mine = " * ";
	private String unPressed = " . ";
	private String flagged = " F ";
	private String detonationPoint = " ! ";
	private final String blank = " ";
	// instance variables pertaining to array dimensions of game board
	private int row = 0;
	private int column = 0;
	// instance variables pertaining to array dimensions of overlay (tiles visible
	// to the player)
	private int overlayRow = 0;
	private int overlayColumn = 0;
	static boolean isFinished = false;

	// declare two 2D arrays representing the game board and overlay
	String[][] board = new String[userDifficulty][userDifficulty];
	String[][] overlay = new String[userDifficulty][userDifficulty];

	// method that creates the game board
	public void createBoard() {

		// runs along every cell of the board
		while (row < board.length) {
			for (column = 0; column < board.length; column++) {

				// fills board with unPressed tiles
				board[column][row] = unPressed;
				// creates a 2-layer frame of whitespace (buffer zone) around game board which
				// prevents ArrayOutOfBoundsException
				board[column][0] = " ";
				board[column][1] = " ";
				board[0][row] = " ";
				board[1][row] = " ";
				board[column][board.length - 1] = " ";
				board[column][board.length - 2] = " ";
				board[board.length - 1][row] = " ";
				board[board.length - 2][row] = " ";

			}
			row++;
		}

	}

	// method that creates the overlay visible to the player
	public void createOverlay() {

		// runs along every cell of the board
		while (overlayRow < overlay.length) {
			for (overlayColumn = 0; overlayColumn < overlay.length; overlayColumn++) {

				// fills overlay with unPressed tiles
				overlay[overlayColumn][overlayRow] = unPressed;
				// creates a 2-layer frame of whitespace around overlay
				overlay[overlayColumn][0] = " ";
				overlay[overlayColumn][1] = " ";
				overlay[0][overlayRow] = " ";
				overlay[1][overlayRow] = " ";
				overlay[overlayColumn][board.length - 1] = " ";
				overlay[overlayColumn][board.length - 2] = " ";
				overlay[board.length - 1][overlayRow] = " ";
				overlay[board.length - 2][overlayRow] = " ";
				// prints the overlay to the console
				System.out.print(overlay[overlayColumn][overlayRow]);

			}
			overlayRow++;
			System.out.println("");
		}

	}

	// method that mines the board
	public void placeMines(int numMines) {

		// loop continues until space is mined
		for (int m = 0; m < numMines; m++) {
			while (true) {
				int column, row = 0;
				// uses the Math.random() method to ensure pseudo-random placement of mines
				column = (int) (userDifficulty * Math.random());
				row = (int) (userDifficulty * Math.random());

				// excludes buffer zones
				if (column > 2 && column < board.length - 2) {
					if (row > 2 && row < board.length - 2) {

						// checks if a mine is already present in a spot
						if (!(board[column][row].equalsIgnoreCase(mine))) {
							board[column][row] = mine;
							break;
						}
					}
				}
			}
		}
	}

	// method which updates the board properly on each turn
	public void updateBoard(int userCol, int userRow, String userOption) {

		column = 0;
		row = 0;

		// executes if user wants to flag a tile
		if (userOption.equalsIgnoreCase("FLAG")) {

			// flags tile on game board and overlay
			board[userCol + 1][userRow + 1] = flagged;
			overlay[userCol + 1][userRow + 1] = flagged;

			// reprints overlay
			while (row < board.length) {

				for (column = 0; column < board.length; column++) {

					System.out.print(overlay[column][row]);

				}
				row++;
				System.out.println("");
			}

			// executes if user wants to uncover a tile
		} else if (userOption.equalsIgnoreCase("UNCOVER")) {

			// if user hits a mine, game ends
			if (board[userCol + 1][userRow + 1] == mine) {
				System.out.println("GAME OVER. You lose :(");
				// detonationPoint is assigned to tile where mine "exploded"
				board[userCol + 1][userRow + 1] = detonationPoint;

				// frame is obscured from player's view
				reassignBuffer();

				// location of all mines on the game board is printed
				row = 2;
				while (row < board.length - 2) {

					for (column = 2; column < board.length - 2; column++) {
						System.out.print(board[column][row]);
					}
					row++;
					System.out.println("");
				}
				// game ends
				System.exit(0);

				// this branch executes if tile chosen is not a mine
			} else if (!(board[userCol + 1][userRow + 1] == mine)) {

				// senseMines method is called. Finds number of mines surrounding selected tile
				senseMines(userCol + 1, userRow + 1);

				// if the number of mines surrounding the selected tile is 0, mines are sensed
				// for all surrounding tiles.
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
				// frame is obscured from player
				reassignBuffer();

				// overlay is printed to console
				while (row < board.length) {
					for (column = 0; column < board.length; column++) {

						System.out.print(overlay[column][row]);

					}
					row++;
					System.out.println("");
				}

			}

			// catch-all branch
		} else {
			System.out.println("ERROR");
		}

	}

	//method that reassigns values within the frame to whitespace
	public void reassignBuffer() {

		//rows and columns for game board and overlay
		overlayRow = 0;
		overlayColumn = 0;
		row = 0;
		column = 0;

		//loops through the board and overlay
		while (overlayRow < board.length) {
			for (overlayColumn = 0; overlayColumn < board.length; overlayColumn++) {

				//creates overlay frame
				overlay[overlayColumn][0] = blank;
				overlay[overlayColumn][1] = blank;
				overlay[0][overlayRow] = blank;
				overlay[1][overlayRow] = blank;
				overlay[overlayColumn][board.length - 1] = blank;
				overlay[overlayColumn][board.length - 2] = blank;
				overlay[board.length - 1][overlayRow] = blank;
				overlay[board.length - 2][overlayRow] = blank;

				//creates game board frame
				board[column][0] = blank;
				board[column][1] = blank;
				board[0][row] = blank;
				board[1][row] = blank;
				board[column][board.length - 1] = blank;
				board[column][board.length - 2] = blank;
				board[board.length - 1][row] = blank;
				board[board.length - 2][row] = blank;

			}
			overlayRow++;

		}
	}

	//method that senses mines surrounding given tile
	public void senseMines(int column, int row) {
		int mineCounter = 0; // counter variable

		//checks tiles surrounding tile in question for mines
		for (int i = (column - 1); i <= (column + 1); i++) {
			for (int j = (row - 1); j <= (row + 1); j++) {
				if ((board[i][j].equalsIgnoreCase(mine)) == true)
					mineCounter++; // when mine is sensed, counter increases
			}
		}

		//only executes if selected location is not already flagged
		if (!(board[column][row] == flagged)) {

			board[column][row] = " " + Integer.toString(mineCounter) + " ";
			overlay[column][row] = " " + Integer.toString(mineCounter) + " ";
		} else {
			return;
		}

	}

	//method to find if user has finished the game by winning
	public boolean isFinished() {

		//counts the number of mines still remaining on the board
		int numMines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].equals(mine) == true)
					numMines++; // increments for each un-cleared location
			}
		}

		//if there are no un-flagged mines still on the board, player wins
		if (numMines == 0) {
			System.out.println("You win! Isn't it wonderful? :D");
			
			//game board is printed
			while (row < board.length) {

				for (column = 0; column < board.length; column++) {
					System.out.print(board[column][row]);
				}
				row++;
				System.out.println("");
			}
			//static field isFinished flips to true, game ends
			isFinished = true;

		}
		return isFinished;

	}

}
package midtermMinefield;

import java.util.Scanner;

public class MinefieldGameApp extends MinefieldGame {

	public static void main(String[] args) {
		
		//variables to store user selections
		int userColumn;
		int userRow;
		
		//stores number of mines which varies based on difficulty chosen
		int numOfMines;
		
		//choice between FLAG and UNCOVER
		String userOption;
		
		//stores difficulty selection
		String difficulty;

		Scanner scnr = new Scanner(System.in);

		//welcomes player
		System.out.println("Welcome to Grand Circus Minefield: Premium Midterm Edition"
				+ "\n\nFlag all the correct mines, and you're a winner, baby!"
				+ "\nHit a mine, and it's curtains for you."
				+ "\n\nCoordinates begin with 1 in the top left corner (the origin)."
				+ "\nThe number of mines increases with the difficulty level."
				+ "\n\nThe EASY difficulty level has 6 squares on each side."
				+ "\nMEDIUM has 10, and HARD has 14.");

		//validates difficulty selection
		difficulty = Validator.getString(scnr, "\nGood luck, and tread carefully!"
				+ "\n\nPlease enter a difficulty level: EASY/MEDIUM/HARD");
		
		//branches for each difficulty selection, determines size of board, which is userDifficulty-4. (4 corresponds to the frame size)
		if (difficulty.equalsIgnoreCase("EASY")) {
			userDifficulty = 10;
		} else if (difficulty.equalsIgnoreCase("MEDIUM")) {
			userDifficulty = 14;
		} else if (difficulty.equalsIgnoreCase("HARD")) {
			userDifficulty = 18;
		} else {
			System.out.println("Invalid difficulty level!");
		}
		
		//determines number of mines based on difficulty setting (set at 25% of tiles)
		numOfMines = (((userDifficulty - 4)  * (userDifficulty - 4)) / 4);
		
		//new instance of MinefieldGame
		MinefieldGame minefield = new MinefieldGame();
		
		//game board and overlay are created
		minefield.createBoard();
		minefield.createOverlay();
		
		//mines are placed
		minefield.placeMines(numOfMines);

	do {
			//user enters a column and number is validated
			userColumn = Validator.getInt(scnr, "Please enter a column: ", 1, (userDifficulty - 4));

			//user enters a row and number is validated
			userRow = Validator.getInt(scnr, "Please enter a row: ", 1, (userDifficulty - 4));

			//user selects FLAG or UNCOVER and String is validated
			userOption = Validator.getString(scnr, "Would you like to flag a mine or uncover a cell? FLAG/UNCOVER");
			if (userOption.equalsIgnoreCase("FLAG")) {
				minefield.updateBoard(userColumn, userRow, "FLAG");
			} else if (userOption.equalsIgnoreCase("UNCOVER")) {
				minefield.updateBoard(userColumn, userRow, "UNCOVER");
			}
			
			//checks if player has won
			minefield.isFinished();
		//loops as game continues, i.e. player hasn't won yet
		} while (!MinefieldGame.isFinished);
}
}
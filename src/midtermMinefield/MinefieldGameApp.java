package midtermMinefield;

import java.util.Scanner;

public class MinefieldGameApp extends MinefieldGame {

	public static void main(String[] args) {
		int userColumn;
		int userRow;
		int numOfMines;
		String userOption;
		String difficulty;

		Scanner scnr = new Scanner(System.in);


		difficulty = Validator.getString(scnr, "Enter a difficulty level: EASY/MEDIUM/HARD");
		
		if (difficulty.equalsIgnoreCase("EASY")) {
			userDifficulty = 10;
		} else if (difficulty.equalsIgnoreCase("MEDIUM")) {
			userDifficulty = 14;
		} else if (difficulty.equalsIgnoreCase("HARD")) {
			userDifficulty = 18;
		} else {
			System.out.println("Invalid difficulty level!");
		}
		numOfMines = (((userDifficulty - 4)  * (userDifficulty - 4)) / 10);
		
		MinefieldGame minefield = new MinefieldGame();
		minefield.createBoard();
		minefield.createOverlay();
		
		minefield.placeMines(numOfMines);

	do {

			userColumn = Validator.getInt(scnr, "Please enter a column: ", 1, (userDifficulty - 4));

			userRow = Validator.getInt(scnr, "Please enter a row: ", 1, (userDifficulty - 4));

			userOption = Validator.getString(scnr, "Would you like to flag a mine or uncover a cell?");
			if (userOption.equalsIgnoreCase("FLAG")) {

				minefield.updateBoard(userColumn, userRow, "FLAG");
			} else if (userOption.equalsIgnoreCase("UNCOVER")) {

				minefield.updateBoard(userColumn, userRow, "UNCOVER");
			}
			
			minefield.isFinished();
		} while (!MinefieldGame.isFinished);
}
}
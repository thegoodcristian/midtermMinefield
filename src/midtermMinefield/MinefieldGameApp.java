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


		System.out.println("Enter a difficulty level: EASY/MEDIUM/HARD");
		difficulty = scnr.next();
		
		if (difficulty.equalsIgnoreCase("EASY")) {
			userDifficulty = 9;
		} else if (difficulty.equalsIgnoreCase("MEDIUM")) {
			userDifficulty = 14;
		} else if (difficulty.equalsIgnoreCase("HARD")) {
			userDifficulty = 19;
		} else {
			System.out.println("Invalid difficulty level!");
		}
		numOfMines = (((userDifficulty - 4)  * (userDifficulty - 4)) / 4);
		
		MinefieldGame minefield = new MinefieldGame();
		minefield.createBoard();
		minefield.createOverlay();
		
		minefield.placeMines(numOfMines);

	do {

			System.out.println("Please enter a column: ");
			userColumn = scnr.nextInt();
			System.out.println("Please enter a row: ");
			userRow = scnr.nextInt();

			System.out.println("Would you like to flag a mine or uncover a cell?");
			userOption = scnr.next();
			if (userOption.equalsIgnoreCase("FLAG")) {

				minefield.updateBoard(userColumn, userRow, "FLAG");
			} else if (userOption.equalsIgnoreCase("UNCOVER")) {

				minefield.updateBoard(userColumn, userRow, "UNCOVER");
			}
			minefield.isFinished();
		} while (!MinefieldGame.isFinished);
}
}
package midtermMinefield;

import java.util.Scanner;

public class MinefieldGameApp extends MinefieldGame {

	public static void main(String[] args) {
		int userColumn;
		int userRow;
		String userOption;

		Scanner scnr = new Scanner(System.in);

		MinefieldGame minefield = new MinefieldGame();

		minefield.createBoard();
		minefield.placeMines(5);

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
		


	}

}

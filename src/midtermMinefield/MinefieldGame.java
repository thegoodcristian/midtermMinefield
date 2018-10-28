package midtermMinefield;

public class MinefieldGame {

	static int userDifficulty = 0;
	private String unPressed = " . ";
	private String flagged = " F ";
	private int row = 0;
	private int column = 0;


	
	//public String[][] getTile(int column, int row) {
		//return [column][row];
	//}
	
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

	
	public void updateBoard(int colIndex, int rowIndex, String userOption) {

		column = 0;
		row = 0;
		
		
		// runs along every cell of the board
		if (userOption.equalsIgnoreCase("FLAG")) {
		
		board[colIndex-1][rowIndex-1] = flagged;
		
		while (row <= board.length - 1) {

			for (column = 0; column < board.length; column++) {

		
				System.out.print(board[column][row]);

			}
			row++;
			System.out.println("");
		}

		}
	}
}


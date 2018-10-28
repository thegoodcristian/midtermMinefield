package midtermMinefield;

public class MinefieldGame {

	private int userDifficulty = 10;
	private String unPressed = " . ";
	private String flagged = " F ";
	private int row = 0;
	private int column = 0;

	String[][] board = new String[userDifficulty][userDifficulty];
	
	//public String[][] getTile(int column, int row) {
		//return [column][row];
	//}
	
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
	
	public void updateBoard() {

		row = 0;
		column = 0;
		
		// runs along every cell of the board
		board[2][2] = flagged;
		
		while (row <= board.length - 1) {

			for (column = 0; column < board.length; column++) {

		
				System.out.print(board[column][row]);

			}
			row++;
			System.out.println("");
		}

	}
}


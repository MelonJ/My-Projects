package Chess;

public class Rook extends ChessPiece {

	private static boolean moved = false;

	/**
	 * Sets "rook" as player
	 * @param player
	 */
	public Rook(Player player) {

		super(player);

	}

	/**
	 * Sets type of player to "rook"
	 * @return
	 */
	public String type() {

		return "Rook";

	}

	/**
	 * Sets move for rook
	 * @param moved
	 */
	public static void setMoved(boolean moved) {
		Rook.moved = moved;
	}

	/**
	 * Checks if rook cant block
	 * @param move
	 * @param board
	 * @return
	 */
	public boolean noBlock(Move move, IChessPiece[][] board){

		//checks to see if the move is in the same row
		if((move.toRow - move.fromRow) == 0){

			//checks if move is from top to bottom or vice versa
			if(move.fromColumn < move.toColumn){

				for(int i = move.toColumn-1; i > move.fromColumn; i--){

					if(board[move.fromRow][i] != null){
						return false;
					}
				}
			}

			else if(move.fromColumn > move.toColumn){

				for(int i = move.fromColumn-1; i > move.toColumn; i--){

					if(board[move.fromRow][i] != null){
						return false;
					}
				}
			}
		}

		//checks to see if the move is in the same row
		if((move.toColumn - move.fromColumn) == 0){

			//checks if move is from left to right or vice versa
			if(move.fromRow < move.toRow){

				for(int i = move.toRow-1; i > move.fromRow; i--){

					if(board[i][move.toColumn] != null){
						return false;
					}
				}
			}

			else if(move.fromRow > move.toRow){

				for(int i = move.fromRow-1; i > move.toRow; i--){

					if(board[i][move.toColumn] != null){
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * determines if the move is valid for a rook piece
	 * @param move  a {@link //W18project3.Move} object describing the move to be made.
	 * @param board the {@link //W18project3.IChessBoard} in which this piece resides.
	 * @return
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid = true;

		if (super.isValidMove(move, board) == !valid) {
			return !valid;
		}

		if (noBlock(move, board)) {

			//if there is an enemy
			if (board[move.toRow][move.toColumn] != null && board[move.toRow][move.toColumn].player() != player() &&
					((move.toRow == move.fromRow) ^ (move.toColumn == move.fromColumn))) {
				setMoved(true);
				return valid;
			}
			//if destination is empty
			if (board[move.toRow][move.toColumn] == null && ((move.toRow == move.fromRow) ^ (move.toColumn == move.fromColumn))) {
				setMoved(true);
				return valid;
			}
		}

		return !valid;
	}
}

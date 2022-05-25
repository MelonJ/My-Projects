package Chess;

public class Knight extends ChessPiece {


	/**
	 * Sets "Knight" as player
	 * @param player
	 */
	public Knight(Player player) {
		super(player);
	}
	/**
	 * Sets type of player to "Knight"
	 * @return
	 */
	public String type() {
		return "Knight";
	}
	/**
	 * Determines if movement is valid, ensure only L-shaped
	 * movement pattern for knight, and checks for enemies/obstacles
	 * @param move
	 * @param board
	 * @return valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board){

		boolean valid = true;

		if(super.isValidMove(move, board) != valid){
			return !valid;
		}

		if(((Math.abs(move.fromColumn - move.toColumn) == 2) && Math.abs(move.fromRow - move.toRow) == 1) ||
				((Math.abs(move.fromColumn - move.toColumn) == 1) && Math.abs(move.fromRow - move.toRow) == 2)){

			return valid;
		}
		return !valid;
	}



}


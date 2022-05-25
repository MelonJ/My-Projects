package Chess;
public class King extends ChessPiece {

	public King(Player player) {
		super(player);
	}

	private static boolean moved = false;
	boolean hasMoved = false;

	public void setHasMoved(boolean bool) {
		hasMoved = bool;
	}

	public static boolean isMoved() {
		return moved;
	}

	public static void setMoved(boolean moved) {
		King.moved = moved;
	}

	public String type() {
		return "King";
	}


	/**
	 * determines if move is valid for king within 3X3 space around the king
	 * @param move
	 * @param board
	 * @return valid
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;

		if(super.isValidMove(move, board) == !valid){
			return !valid;
		}

		//So long as there is no piece of same color
		if((Math.abs(move.toRow - move.fromRow) <= 1) &&
				(Math.abs(move.toColumn - move.fromColumn) <= 1)){
			setMoved(true);
			return valid;
		}
		return !valid;
	}

}

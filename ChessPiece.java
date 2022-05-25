package Chess;

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	/**
	 * Chess Piece sets to owner
	 * @param player
	 */
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	/**
	 * sets Player = Owner;
	 * @return
	 */
	public Player player() {
		return owner;
	}

	/**
	 * Checks if move is valid for general piece
	 * @param move  a object describing the move to be made.
	 * @param board the in which this piece resides.
	 * @return
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = true;

		if (((move.fromRow == move.toRow) && (move.fromColumn ==
				move.toColumn))) {
			valid = false;

		}
		if(move.toColumn < 0 || move.toRow < 0 || move.toColumn >
				board.length - 1 || move.toRow > board.length - 1) {
			valid = false;
		}
		if (board[move.toRow][move.toColumn] != null &&
				board[move.toRow][move.toColumn].player() == player()){
			valid = false;
		}
		return valid;
	}
}


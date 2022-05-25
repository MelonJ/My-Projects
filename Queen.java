package Chess;

public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);

	}

	/**
	 * Sets type of player to "Queen"
	 * @return
	 */
	public String type() {
		return "Queen";

	}
	/**
	 * Checks if move is valid for queen
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());
		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}


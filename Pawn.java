package Chess;

public class Pawn extends ChessPiece {


	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "Pawn";
	}

	private static final int WHITE_STARTING_ROW = 2;
	private static final int BLACK_STARTING_ROW = 6;
	private static final int WHITE_PAWN_DISTANCE = -1;
	private static final int BLACK_PAWN_DISTANCE = 1;
	private static final int PAWN_START_DISTANCE = 1;



	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}
		if (player() == Player.WHITE) {
			// if there is an enemy piece in front of you
			if ((board[move.toRow][move.toColumn] != null &&
					board[move.toRow][move.toColumn].player() !=
							player())
					&& (move.fromRow - move.toRow == 1 &&
					Math.abs(move.fromColumn - move.toColumn) ==
							1)) {
				return true;
			} else if((move.fromRow == 6 && board[move.toRow]
					[move.toColumn] == null) && Math.abs(move.toColumn
					- move.fromColumn) == 0 && move.toRow - move.fromRow
					== -2) {
				return true;
			} else if (board[move.toRow][move.toColumn] == null &&
					Math.abs(move.toColumn - move.fromColumn) == 0
					&& move.toRow - move.fromRow == -1) {
				return true;
			}
		} else {
			if (((board[move.toRow][move.toColumn] != null &&
					board[move.toRow][move.toColumn].player()
							!= player())
					&& (move.fromRow - move.toRow == -1 &&
					Math.abs(move.fromColumn - move.toColumn)
							== 1))) {
				return true;
			} else if((move.fromRow == 1 && board[move.toRow]
					[move.toColumn] == null) && Math.abs(move.toColumn
					- move.fromColumn) == 0 && move.toRow -
					move.fromRow == 2) {
				return true;
			}else if (board[move.toRow][move.toColumn] == null
					&& Math.abs(move.toColumn - move.fromColumn)
					== 0 && move.toRow - move.fromRow == 1) {
				return true;
			}
		}
		return false;
	}

}

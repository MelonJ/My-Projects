package Chess;

public class Move {
	public int fromRow, fromColumn, toRow, toColumn;

	public Move() {
	}

	/**
	 * Initializes rows,Column (to and from) for
	 * @param fromRow
	 * @param fromColumn
	 * @param toRow
	 * @param toColumn
	 */
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}

	/**
	 *
	 * @param move
	 * @return
	 */
	public boolean equals(Move move){
		return this.fromRow == move.fromRow && this.fromColumn == move.fromColumn
				&& this.toRow == move.toRow && this.toColumn == move.toColumn;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
	
	
}

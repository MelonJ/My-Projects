package Chess;

	public class Bishop extends ChessPiece {

    public Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    /**
     * Method check if bishop is being blocked. If so, returns false
     * else true
     *
     * @param move
     * @param board
     * @return valid
     */
    public boolean noBlock(Move move, IChessPiece[][] board) {

        boolean valid = true;

        //first check is for up and to the right
        if ((move.fromColumn < move.toColumn) && (move.fromRow > move.toRow)) {

            for (int c = move.fromColumn + 1, r = move.fromRow - 1; c < move.toColumn && r > move.toRow; c++, r--) {

                if (board[r][c] != null) {
                    valid = false;
                }
            }

        }
        //second check is for down and to the right
        if ((move.fromColumn < move.toColumn) && (move.fromRow < move.toRow)) {

            for (int c = move.fromColumn + 1, r = move.fromRow + 1; c < move.toColumn && r < move.toRow; r++, c++) {

                if (board[r][c] != null) {
                    valid = false;
                }

            }
        }
        //third check is for up and to the left
        if ((move.fromColumn > move.toColumn) && (move.fromRow > move.toRow)) {

            for (int c = move.fromColumn - 1, r = move.fromRow - 1; c > move.toColumn && r > move.toRow; c--, r--) {

                if (board[r][c] != null) {
                    valid = false;
                }
            }

        }

        //forth check is for down and to the left
        if ((move.fromColumn > move.toColumn) && (move.fromRow < move.toRow)) {

            for (int c = move.fromColumn - 1, r = move.fromRow + 1; c > move.toColumn && r < move.toRow; c--, r++) {

                if (board[r][c] != null) {
                    valid = false;
                }

            }
        }

        return valid;
    }

    /**
     * Checks if destination is blocked, empty, or has an enemy and
     * returns valid based on those checks
     *
     * @param move
     * @param board
     * @return valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        // More code is needed
        if (super.isValidMove(move, board) == valid) {
            return valid;
        }

        if ((Math.abs(move.fromRow - move.toRow)) == (Math.abs(move.fromColumn - move.toColumn))) {
            valid = true;
        }

        if (!noBlock(move, board)) {

            valid = false;
        }

        return valid;
    }

}


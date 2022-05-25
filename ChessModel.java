package Chess;
import javax.swing.*;
import java.util.Stack;

/************************************************************************************
 * A Game of Chess is a simple chess game model and a simple GUI program that allows two
 * humans to play the chess game. The design provided organizes the different pieces into a class hierarchy
 * that utilizes polymorphism.
 *
 * @authors Jaiden Ortiz, Jaime Malone, Mohamed Abdirahman
 ************************************************************************************/


public class ChessModel implements IChessModel {
	Stack<IChessPiece[][]> history;
	private IChessPiece[][] board;
	private Player player;

	//Constructor
	public ChessModel() {
		board = new IChessPiece[8][8];
		player = Player.WHITE;
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(Player.WHITE);
			board[1][i] = new Pawn(Player.BLACK);
		}

		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		history = new Stack<IChessPiece[][]>();
	}
	/***************************************************
	 * Method returns whether checkmate has occurred
	 *
	 * @return true if there is a checkmate, else false
	 ****************************************************/
	public boolean isComplete() {

		boolean valid = true;

		if(inCheck(currentPlayer())){
			int kingRow = -1;
			int kingCol = -1;
			//Locating players p's king
			for (int row = 0; row < numRows(); row++) {
				for (int col = 0; col < numColumns(); col++) {
					if (board[row][col] != null && board[row][col].type().equals("King") && board[row][col].player() == currentPlayer()) {

						kingRow = row;
						kingCol = col;
						break;
					}
				}
			}

			//Checks whether new space is valid move and in check

			for (int toRow = 0; toRow < numRows(); toRow++) {
				for (int toCol = 0; toCol < numColumns(); toCol++){
					if (pieceAt(kingRow, kingCol).isValidMove(new Move(kingRow, kingCol, toRow, toCol), board)){

						if(!inCheck(currentPlayer())) {

							return !valid;
						}
					}
				}
			}

			//checks if a player piece can block check
			for (int row = 0; row < numRows(); row++) {
				for (int col = 0; col < numColumns(); col++) {
					if(board[row][col] != null && board[row][col].player() == currentPlayer()){


						for(int r = 0; r < numRows(); r++){
							for(int c = 0; c < numColumns(); c++){


								if(pieceAt(row,col).isValidMove(new Move(row,col,r,c), board)){

									move(new Move(row,col,r,c));

									if(!inCheck(currentPlayer())){

										return !valid;
									}

									else{

										move(new Move(r,c,row,col));
									}
								}
							}
						}
					}
				}
			}
		}


		JOptionPane.showMessageDialog(null, "CheckMate! Player: " + currentPlayer() + "Wins!");
		return valid;

	}

		/***************************************************
         * Method tells us whether a move is valid for
         * any given chess piece.
         * @param move
         * @return True if valid move, else false
         ****************************************************/
		public boolean isValidMove(Move move) {
			boolean valid = false;

			if (board[move.fromRow][move.fromColumn] != null)
				if (board[move.fromRow][move.fromColumn].isValidMove(move, board))
					return true;

			JOptionPane.showMessageDialog(null, "Invalid Move");
			return valid;
		}

	/***************************************************
	 * Sets method to use to make moves
	 * @param move a {@link //W18project3.Move} object describing the move to be made.
	 ****************************************************/
	public void move(Move move) {
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}
	/***************************************************
	 * Sets method to use to make moves
	 * @param move a {@link //W18project3.Move} object describing the move to be made.
	 ****************************************************/

	public void move(Move move, IChessPiece[][] board) {
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/******************************************************************
	 * Method tells us whether player's king is in check
	 * @param p
	 * @return True if in check, else false
	 */
	public boolean inCheck(Player p) {

		boolean valid = true;

		int kingRow = -1;
		int kingCol = -1;
		//Locating players p's king
		for (int row = 0; row < numRows(); row++) {
			for (int col = 0; col < numColumns(); col++) {
				if (board[row][col] != null && board[row][col].type().equals("King") && board[row][col].player() == p) {
					kingRow = row;
					kingCol = col;
					break;
				}
			}
		}

		//Seeing if opposite player piece within range of king
		for (int row = 0; row < numRows(); row++){
			for (int col = 0; col < numColumns(); col++) {
				if (board[row][col] != null && pieceAt(row,col).player() != p) {
					if (board[row][col].isValidMove(new Move(row, col, kingRow, kingCol), board)) {

						return valid;
					}
				}
			}
		}

		return !valid;
	}

	/**
	 * Method tells us whether player's king is in check
	 *
	 * @param p, row, column
	 * @return True if in check, else false
	 */
	public boolean inCheck(Player p, int toRow, int toCol){
		boolean valid = true;

		int toKingRow = toRow;
		int toKingCol = toCol;

		//Seeing if opposite player piece within range of king
		for (int row = 0; row < numRows(); row++)
			for (int col = 0; col < numColumns(); col++)
				if (board[row][col] != null && pieceAt(row,col).player() != currentPlayer()) {

					if (pieceAt(row, col).isValidMove(new Move(row, col, toKingRow, toKingCol), board)) {

						return valid;
					}
				}

		return !valid;
	}

	/**
	 * @return player, current player
	 */
	public Player currentPlayer() {
		return player;
	}

	/**
	 * @return number of rows
	 */
	public int numRows() {
		return 8;
	}

	/**
	 * @return number of columns
	 */
	public int numColumns() {
		return 8;
	}

	/**
	 * returns
	 * @return board[row][column]
	 */
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/**
	 * Switches players
	 */
	public void setNextPlayer() {
		player = player.next();
	}

	/**
	 * returns king position
	 */
	public String getKingPosition() {
		int kingRow = -1;
		int kingCol = -1;
		//Locating players p's king
		for (int row = 0; row < numRows(); row++) {
			for (int col = 0; col < numColumns(); col++) {
				if (board[row][col] != null && board[row][col].type().equals("King") && board[row][col].player() == currentPlayer()) {
					//System.out.println("Hello");
					kingRow = row;
					kingCol = col;
					//break;
				}
			}
		}
		return (kingRow + ", " + kingCol);
	}

	/**
	 * Resets all board pieces
	 */
	public void resetBoard(){

		player = Player.WHITE;

		//clear board
		for(int row = 0; row < numRows(); row++){
			for(int col = 0; col < numColumns(); col++){
				board[row][col] = null;
			}
		}

		//BACKLINE PIECES FOR PLAYER
		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight (Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		//FRONTLINE PIECES FOR PLAYER
		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);

		//BACKLINE PIECES FOR PLAYER
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight (Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		//FRONTLINE PIECES FOR PLAYER
		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);

	}


	/**
	 * Sets a given piece to specific location on the board
	 *
	 * @param row
	 * @param column
	 * @param piece
	 */
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	/**
	 * undo the moves that the player has made
	 */
	public void undo() {

		if (history.size() == 0) {
			return;
		}

		board = history.pop(); // Returns the top thing on the stack and also removes it from the stack.

		//change player
		setNextPlayer();
	}

	/**
	 * Makes a copy of board then stores
	 * every move made into new copy. When Undo()
	 * pushes copy onto stack.
	 */
	public void recordHistory() {
		//deep copy of board
		IChessPiece[][] boardCopy = new IChessPiece[8][8];
		for (int row = 0; row < numRows(); row++) {
			for (int col = 0; col < numColumns(); col++) {
				boardCopy[row][col] = board[row][col];
			}
		}
		//push copy onto stack
		history.push(boardCopy);
	}

	/**
	 * returns opposite player
	 */
	public Player getOppositePlayer() {
		if (currentPlayer().equals(Player.WHITE)) {
			return Player.BLACK;
		} else return Player.WHITE;
	}

	/**
	 * AI method to go against player and
	 * trys to win
	 */
	public void AI() {
		ChessModel cm = new ChessModel();
		//Check to see if you are in check.
		//If so, get out of check by moving the king or placing a piece to block the check
		if (inCheck(currentPlayer())) {
			int kingRow = -1;
			int kingCol = -1;
			//Locating players p's king
			for (int row = 0; row < numRows(); row++) {
				for (int col = 0; col < numColumns(); col++) {
					if (board[row][col] != null && board[row][col].type().equals("King") && board[row][col].player() == currentPlayer()) {
						kingRow = row;
						kingCol = col;
						break;
					}
				}
			}

			//Checks whether new space is valid move and in check if so, then move king to space
			for (int toRow = 0; toRow < numRows(); toRow++) {
				for (int toCol = 0; toCol < numColumns(); toCol++) {
					if (pieceAt(kingRow, kingCol) != null && pieceAt(kingRow, kingCol).isValidMove(new Move(kingRow, kingCol, toRow, toCol), board) && !inCheck(currentPlayer(), toRow, toCol))
					{
						this.move(new Move(kingRow, kingCol, toRow, toCol));
						System.out.println(pieceAt(4, 4));
					}
				}
			}

			for (int row = 0; row < numRows(); row++) {
				for (int col = 0; col < numColumns(); col++) {
					if (board[row][col] != null && board[row][col].player() == currentPlayer()) {

						for (int r = 0; r < numRows(); r++) {
							for (int c = 0; c < numColumns(); c++) {
								if (pieceAt(row, col) != null && (board[row][col].player() != currentPlayer() || board[row][col] == null)
										&& (pieceAt(row, col).isValidMove(new Move(row, col, r, c), board))) {
									move(new Move(row, col, r, c));
									if (!inCheck(currentPlayer())) {
									} else {
										return;
									}
								}
							}
						}
					}
				}
			}
		} else {
			for (int row = 0; row < numRows(); row++) {
				for (int col = 0; col < numColumns(); col++) {
					if (board[row][col] != null && board[row][col].player() == currentPlayer()) {
						for (int r = 0; r < numRows(); r++) {
							for (int c = 0; c < numColumns(); c++) {
								if (pieceAt(row, col) != null && (board[row][col].player() != currentPlayer() || board[row][col] == null) &&
										(pieceAt(row, col).isValidMove(new Move(row, col, r, c), board))) {
									move(new Move(row, col, r, c));
									if (!inCheck(getOppositePlayer())) {
									} else {
										return;
									}
								}
							}
						}
					}
				}
			}
			//Moves that will be made when a move has to be made and all else fails
			for (int row = (int) (Math.round(Math.random())); row < numRows(); row++) {
				for (int col = (int) (Math.round(Math.random() * 7)); col < numColumns(); col++) {
					if (board[row][col] != null && board[row][col].player() == currentPlayer()) {
						for (int r = 0; r < numRows(); r++) {
							for (int c = 0; c < numColumns(); c++) {
								if (pieceAt(row, col) != null && pieceAt(row, col).isValidMove(new Move(row, col, r, c), board)) {
									System.out.println("AI");
									move(new Move(row, col, r, c));
									return;
								}
							}
						}
					}
				}
			}
		}
	}
}

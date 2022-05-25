package Chess;
import org.junit.Test;
import static org.junit.Assert.*;


public class ChessModelTest {

    private IChessPiece[][] board;

    /**
     * Move object for test
     */
    private Move move;

    /**
     * ChessModel object for test
     */
    private ChessModel model;

    public ChessModelTest() {

        // creates  2D array board of IChessPiece objects
        board = new IChessPiece[8][8];

        // instantiates a new ChessModel object
        model = new ChessModel();
    }

    /**
     * Helper method for tests
     */
    private void resetBoard() {

        // creates a new 2-D array (board) of IChessPiece objects
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = null;
            }
        }
    }

    /**
     * Tests for different pawn movements
     */
    @Test
    public void testPawnMoves() {
        resetBoard();

        // put White pawn into place to test valid and invalid moves
        Pawn p = new Pawn(Player.WHITE);
        board[5][4] = p;

        // Check if pawn can move on to the same spot its already on
        //Should be false
        move = new Move(5, 4, 5, 4);
        assertFalse(p.isValidMove(move, board));

        //Put a white piece in front of other white piece and try
        //to jump each other; Should be false
        Pawn bPawn = new Pawn(Player.WHITE);
        board[4][4] = bPawn;
        move = new Move(5, 4, 3, 4);
        assertFalse(p.isValidMove(move, board));

        resetBoard();

    }

    @Test
    public void testIsValidMoveBlackRook() {
        model.move(new Move(1,0,4,0));
        assertTrue(model.isValidMove(new Move(0,0,1,0)));
        model.move(new Move(0,0,1,0));
        assertEquals("Rook", model.pieceAt(1,0).type());
    }

    @Test
    public void testIsValidMoveBlackQueen() {
        model.move(new Move(1,2,3,1));
        assertTrue(model.isValidMove(new Move(0,3,1,2)));
        model.move(new Move(0,3,1,2));
        assertEquals("Queen", model.pieceAt(1,2).type());
    }

    @Test
    public void testIsValidMoveBlackKnight() {
        assertTrue(model.isValidMove(new Move(0,1,2,0)));
        model.move(new Move(0,1,2,0));
        assertEquals("Knight", model.pieceAt(2,0).type());
    }

    @Test
    public void testIsValidMoveBlackPawn() {
        assertTrue(model.isValidMove(new Move(1,1,2,1)));
        model.move(new Move(1,1,2,1));
        assertEquals("Pawn", model.pieceAt(2,1).type());
    }

    @Test
    public void testIsValidMoveBlackBishop() {
        model.move(new Move(1,1,3,0));
        assertTrue(model.isValidMove(new Move(0,2,1,1)));
        model.move(new Move(0,2,1,1));
        assertEquals("Bishop", model.pieceAt(1,1).type());
    }

    @Test
    public void testPawnMoves2() {
        Pawn p = new Pawn(Player.WHITE);
        board[5][4] = p;

        //Put a white piece in front of other white peice and try
        //to jump each other; Should be flase
        Pawn bPawn = new Pawn(Player.WHITE);
        board[4][4] = bPawn;
        move = new Move(5, 4, 3, 4);
        ////////
        //Take off pawn from before and make new 'p' and  move forward
        board[5][4] = null;
        board[4][4] = p;
        move = new Move(4, 4, 3, 3);
    }
    @Test
    public void testPawnMoves3() {
        Pawn p = new Pawn(Player.WHITE);

        //Sees if you can put piece outside of board size
        board[4][4] = null;
        board[7][7] = p;
        move = new Move(7, 7, 7, 7);
        assertFalse(p.isValidMove(move, board));
    }


    @Test
    public void testInCheckAndIsComplete(){
        resetBoard();
        Rook r = new Rook(Player.WHITE);
        King k = new King(Player.BLACK);

        model.move(new Move(7, 4, 4, 5)); // moving king
        model.move(new Move(0, 3, 5, 5));

        assertTrue("inCheck() should be true", model.inCheck(Player.WHITE));
        assertFalse(model.isComplete());
    }

    @Test
    public void testAIAndInCheck(){
        //cm.nullBoard();
        IChessPiece k = model.pieceAt(7, 4);
        IChessPiece r = model.pieceAt(0, 7);

        model.move(new Move(7, 4, 4, 5));
        model.move(new Move(0, 7, 5, 5));

        model.getKingPosition();

        assertTrue(model.inCheck(Player.WHITE));

        model.AI();

        model.getKingPosition();

        assertFalse(model.inCheck(Player.WHITE));
    }

    @Test
    public void testAIAndInCheckPawn(){
        //cm.nullBoard();
        IChessPiece k = model.pieceAt(7, 4);
        IChessPiece r = model.pieceAt(0, 7);

        model.move(new Move(7, 4, 4, 5));
        model.move(new Move(1, 7, 3, 6));

        assertTrue(model.inCheck(Player.WHITE));

        model.AI();

        assertFalse(model.inCheck(Player.WHITE));
    }

    @Test
    public void testAIAndInCheckBlock(){
        //cm.nullBoard();
        IChessPiece k = model.pieceAt(7, 4);
        IChessPiece r = model.pieceAt(0, 7);

        model.move(new Move(7, 4, 4, 5)); //moving white king
        model.move(new Move(0, 3, 3, 7)); //moving black queen
        model.move(new Move(0, 7, 5, 3)); //moving black rook
        model.move(new Move(0, 0, 4, 3));   //moving black rook
        model.move(new Move(7,0,2,4));  //moving white rook

        assertTrue(model.inCheck(Player.WHITE));
        model.AI();
    }

    @Test
    public void testIsCompleteInvalid(){
        resetBoard();
        King k = new King(Player.WHITE);
        Rook r = new Rook(Player.BLACK);

        model.move(new Move(7, 4, 4, 5));
        model.move(new Move(0, 7, 5, 5));
        assertFalse(model.isComplete());
    }

    @Test
    public void testMove(){
        IChessPiece k = model.pieceAt(7,4);

        System.out.println(model.pieceAt(7,4));

        assertTrue(model.pieceAt(7,4) == k);

        model.move(new Move(7, 4, 4, 5));

        System.out.println(model.pieceAt(4,5));

        assertTrue(model.pieceAt(4, 5) == k);
    }
}




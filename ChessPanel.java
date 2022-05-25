package Chess;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChessPanel extends JPanel {
    /**
     * All Buttons, Labels, and ImageIcon Needed
     */
    private JButton[][] board;
    private ChessModel model;
    private JLabel turnLabel;
    private JButton undoButton;
    private JButton AIButton;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    private listener listener;

    /**
     * Initializes all listeners for buttons
     * and all borders being used on Chess board
     * panel.
     */
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();


        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));
        buttonpanel.setLayout(new GridLayout(3, 9, 1, 1));

        turnLabel = new JLabel("Turn: " + model.currentPlayer());
        undoButton = new JButton("Undo");
        AIButton = new JButton("AI");

        buttonpanel.add(turnLabel, BorderLayout.NORTH);

        buttonpanel.add(undoButton, BorderLayout.SOUTH);
        undoButton.addActionListener(listener);

        buttonpanel.add(AIButton, BorderLayout.SOUTH);
        AIButton.addActionListener(listener);

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if(model.pieceAt(r,c).player() == Player.BLACK)
                    placeBlackPieces(r,c);
                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /**
     * Set background Color for panel.
     * @param r
     * @param c
     */
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /**
     *  Sets placement of all white Pieces
     *  on board.
     * @param r
     * @param c
     */
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }
    /**
     * Sets placement of all Black Pieces
     * @param r
     * @param c
     */
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * Sets the Image for white & Black player pieces
     *
     */
    private void createIcons() {

        bRook = new ImageIcon("./src/Chess/bRook.png");
        bBishop = new ImageIcon("/Users/hba/Desktop/Chess_edit/src/Chess/bBishop.png");
        bQueen = new ImageIcon("./src/Chess/bQueen.png");
        bKing = new ImageIcon("./src/Chess/bKing.png");
        bPawn = new ImageIcon("./src/Chess/bPawn.png");
        bKnight = new ImageIcon("./src/Chess/bKnight.png");

        wRook = new ImageIcon("./src/Chess/wRook.png");
        wBishop = new ImageIcon("/Users/hba/Desktop/Chess_edit/src/Chess/wBishop.png");
        wQueen = new ImageIcon("./src/Chess/wQueen.png");
        wKing = new ImageIcon("./src/Chess/wKing.png");
        wPawn = new ImageIcon("./src/Chess/wPawn.png");
        wKnight = new ImageIcon("./src/Chess/wKnight.png");

    }


    /**
     * Method that updates the board
     */
    private void displayBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);
                }
                else if(model.pieceAt(r,c).player() == Player.BLACK){

                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
                }
        }

        repaint();
    }

    /**
     *  inner class that represents action listener for multiple buttons.
     */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            boolean flag = false;
            if (undoButton == event.getSource()) {  // undoButton clicked
                model.undo();
                displayBoard();
            }
            if (AIButton == event.getSource() || flag == true) {
                flag = true;
                while (model.currentPlayer() == Player.BLACK) {
                    model.AI();
                    model.recordHistory();
                    displayBoard();
                    model.setNextPlayer();
                    turnLabel.setText("Turn: " + model.currentPlayer());

                }
            }

            for (int r = 0; r < model.numRows(); r++) {
                for (int c = 0; c < model.numColumns(); c++) {
                    if (board[r][c] == event.getSource()) {
                        if (firstTurnFlag == true && model.pieceAt(r, c) != null && model.currentPlayer() == model.pieceAt(r, c).player()) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else if (firstTurnFlag == false) {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m1 = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m1))) {

                                model.move(m1);
                                model.recordHistory(); //for stack undo
                                model.setNextPlayer();
                                turnLabel.setText("Turn: " + model.currentPlayer());
                                displayBoard();
                            }
                            if(model.inCheck(model.currentPlayer())){
                                if (model.isComplete()) {

                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Game over. " +
                                                    model.currentPlayer()
                                                    + " has lost");
                                    model.resetBoard();
                                    displayBoard();
                                }
                                JOptionPane.showMessageDialog(null,model.currentPlayer() + " is in check");
                            }
                        }
                    }
                }
            }

            }
        }
    }




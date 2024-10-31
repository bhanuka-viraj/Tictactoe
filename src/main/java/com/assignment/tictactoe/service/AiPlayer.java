package com.assignment.tictactoe.service;

public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

//    public void move(){
//        int maxScore = Integer.MIN_VALUE;
//        int row = -1;
//        int col = -1;
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board.isLegalMove(i, j)) {
//                    board.updateMove(i,j,Piece.O);
//
//                    int score = minimax(board,0,false);
//                    board.updateMove(i,j,Piece.EMPTY);
//
//                    if (score > maxScore) {
//                        maxScore = score;
//                        row = i;
//                        col = j;
//                    }
//                }
//            }
//        }
//
//        if (row != -1 && col != -1) {
//            move(row,col);
//        }
//    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        Winner winner = board.checkWinner();

        // Base case: Check for terminal state (win/loss/draw)
        if (winner != null) {
            if (winner.winningPiece == Piece.O) {
                return 10 - depth; // AI wins, prefer quicker wins
            } else if (winner.winningPiece == Piece.X) {
                return depth - 10; // Human wins, prefer delaying the loss
            }
        }

        if (isBoardFull(board)) {
            return 0; // Draw
        }

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, Piece.O); // Assume AI (O) plays here

                        int score = minimax(board, depth + 1, false);
                        board.updateMove(i, j, Piece.EMPTY); // Undo the move

                        maxScore = Math.max(maxScore, score);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, Piece.X); // Assume Human (X) plays here

                        int score = minimax(board, depth + 1, true);
                        board.updateMove(i, j, Piece.EMPTY); // Undo the move

                        minScore = Math.min(minScore, score);
                    }
                }
            }
            return minScore;
        } // Placeholder return value
    }

    private boolean isBoardFull(Board board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isLegalMove(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void move(int row, int col) {
//        board.updateMove(row, col, Piece.O);
//        board.getBoardUI().update(row,col,false);

        int maxScore = Integer.MIN_VALUE;
        row = -1;
        col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isLegalMove(i, j)) {
                    board.updateMove(i, j, Piece.O);

                    int score = minimax(board, 0, false);
                    board.updateMove(i, j, Piece.EMPTY);

                    if (score > maxScore) {
                        maxScore = score;
                        row = i;
                        col = j;
                    }
                }
            }
        }

        if (row != -1 && col != -1) {
            board.updateMove(row, col, Piece.O);
            board.getBoardUI().update(row, col, false);
        }
    }
}

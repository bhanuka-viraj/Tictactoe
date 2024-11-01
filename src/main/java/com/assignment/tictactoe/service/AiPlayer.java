package com.assignment.tictactoe.service;

public class AiPlayer extends Player {
    private int maxDepth = 3;
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void move(int row, int col) {
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

    private int minimax(Board board, int depth, boolean isMaximizing) {
        Winner winner = board.checkWinner();

        if (winner != null) {
            if (winner.winningPiece == Piece.O) {
                return 10 - depth;
            } else if (winner.winningPiece == Piece.X) {
                return depth - 10;
            }
        }

        if (depth==maxDepth || isBoardFull(board)) {
            return 0; // Draw
        }

        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, Piece.O);

                        int score = minimax(board, depth + 1, false);
                        board.updateMove(i, j, Piece.EMPTY);

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
                        board.updateMove(i, j, Piece.X);

                        int score = minimax(board, depth + 1, true);
                        board.updateMove(i, j, Piece.EMPTY);

                        minScore = Math.min(minScore, score);
                    }
                }
            }
            return minScore;
        }
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
}


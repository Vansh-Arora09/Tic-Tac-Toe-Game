package com.mycompany.game;

import java.util.Scanner;

public class TicTacToeGame {

    private char[][] board;
    private char currentPlayer;
    private boolean gameActive;

    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameActive = true;
        initializeBoard();
    }

    // Initialize the board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the current board state to the console
    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Switch turns between players
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if any row, column, or diagonal has three of the same player mark
    private boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        // Check main diagonal (top-left to bottom-right)
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        // Check anti-diagonal (top-right to bottom-left)
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Check if all cells are filled (Draw)
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Found an empty spot, not a draw yet
                }
            }
        }
        return true; // No empty spots found, it's a draw
    }

    // Main game loop logic
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Console Tic-Tac-Toe!");
        System.out.println("Enter row and column numbers (0-2) to place your mark.");

        while (gameActive) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter row (0-2): ");
            int row = -1;
            int col = -1;

            // Input Validation Loop
            while (true) {
                try {
                    row = scanner.nextInt();
                    System.out.println("Enter column (0-2): ");
                    col = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter numbers (0, 1, or 2).");
                    scanner.next(); // Clear the invalid input
                    continue;
                }

                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    if (board[row][col] == '-') {
                        break; // Valid move
                    } else {
                        System.out.println("That cell is already occupied. Try again.");
                    }
                } else {
                    System.out.println("Invalid row or column number. Must be between 0 and 2.");
                }
            }

            // Make the move
            board[row][col] = currentPlayer;

            // Check Game Status
            if (checkWin()) {
                printBoard();
                System.out.println("Game Over! Player " + currentPlayer + " wins!");
                gameActive = false;
            } else if (checkDraw()) {
                printBoard();
                System.out.println("Game Over! It's a draw!");
                gameActive = false;
            } else {
                switchPlayer();
            }
        }

        scanner.close();
        System.out.println("Thanks for playing!");
    }

    // Main method to start the application
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
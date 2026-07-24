class Solution {

    public void solveSudoku(char[][] board) {
        solve(board);
    }

    public boolean solve(char[][] board) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {

                    for (char ch = '1'; ch <= '9'; ch++) {

                        if (isSafe(board, row, col, ch)) {

                            // Place number
                            board[row][col] = ch;

                            // Solve remaining board
                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = '.';
                        }
                    }

                    // No digit worked
                    return false;
                }
            }
        }

        // Entire board filled
        return true;
    }

    public boolean isSafe(char[][] board, int row, int col, char ch) {

        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == ch)
                return false;
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == ch)
                return false;
        }

        // Check 3×3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {

                if (board[i][j] == ch)
                    return false;
            }
        }

        return true;
    }
}
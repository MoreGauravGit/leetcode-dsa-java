import java.util.*;

class Solution {

    List<List<String>> ans = new ArrayList<>();
    char[][] board;
    int n;

    public List<List<String>> solveNQueens(int n) {

        this.n = n;
        board = new char[n][n];

        // Fill board with '.'
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        solve(0);

        return ans;
    }

    public void solve(int row) {

        // Base Case
        if (row == n) {
            saveBoard();
            return;
        }

        // Try every column
        for (int col = 0; col < n; col++) {

            if (isSafe(row, col)) {

                // Place Queen
                board[row][col] = 'Q';

                // Next Row
                solve(row + 1);

                // Backtrack
                board[row][col] = '.';
            }
        }
    }

    public boolean isSafe(int row, int col) {

        // Check upper column
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q')
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0;
             i--, j--) {

            if (board[i][j] == 'Q')
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n;
             i--, j++) {

            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    public void saveBoard() {

        List<String> temp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            temp.add(new String(board[i]));
        }

        ans.add(temp);
    }
}
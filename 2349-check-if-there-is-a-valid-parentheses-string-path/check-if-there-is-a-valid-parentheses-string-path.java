class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int length = m + n - 1;

        // Valid parentheses string must have even length
        if (length % 2 != 0) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        // dp[row][col][balance]
        boolean[][][] dp =
                new boolean[m][n][length + 1];

        // Starting cell '('
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                // balance can be at most length - 1
                for (int balance = 0;
                     balance < length;
                     balance++) {

                    // Come from top
                    if (i > 0 && dp[i - 1][j][balance]) {

                        int newBalance;

                        if (grid[i][j] == '(') {
                            newBalance = balance + 1;
                        } else {
                            newBalance = balance - 1;
                        }

                        if (newBalance >= 0 &&
                            newBalance <= length) {

                            dp[i][j][newBalance] = true;
                        }
                    }

                    // Come from left
                    if (j > 0 && dp[i][j - 1][balance]) {

                        int newBalance;

                        if (grid[i][j] == '(') {
                            newBalance = balance + 1;
                        } else {
                            newBalance = balance - 1;
                        }

                        if (newBalance >= 0 &&
                            newBalance <= length) {

                            dp[i][j][newBalance] = true;
                        }
                    }
                }
            }
        }

        // Valid path must end with balance 0
        return dp[m - 1][n - 1][0];
    }
}
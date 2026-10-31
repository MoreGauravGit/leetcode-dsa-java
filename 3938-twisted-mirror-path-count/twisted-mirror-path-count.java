class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // Destination after entering a cell from the right or down direction
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // -1 means invalid path
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (grid[i][j] == 0) {
                    right[i][j] = i * n + j;
                    down[i][j] = i * n + j;
                } else {

                    // Enter mirror while moving right:
                    // Reflection moves down.
                    if (i + 1 < m) {
                        right[i][j] = down[i + 1][j];
                    } else {
                        right[i][j] = -1;
                    }

                    // Enter mirror while moving down:
                    // Reflection moves right.
                    if (j + 1 < n) {
                        down[i][j] = right[i][j + 1];
                    } else {
                        down[i][j] = -1;
                    }
                }
            }
        }

        // DP: number of ways to reach each cell
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }

                int ways = dp[i][j];

                // Move right
                if (j + 1 < n) {
                    int dest = right[i][j + 1];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (int)(
                            (dp[r][c] + (long) ways) % MOD
                        );
                    }
                }

                // Move down
                if (i + 1 < m) {
                    int dest = down[i + 1][j];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (int)(
                            (dp[r][c] + (long) ways) % MOD
                        );
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
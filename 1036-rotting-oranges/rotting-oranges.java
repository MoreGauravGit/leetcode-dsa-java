import java.util.*;

class Solution {

    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        int fresh = 0;

        // 1. Find all initially rotten oranges
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }

                else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // Up, Down, Left, Right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int minutes = 0;

        // 2. BFS
        while (!q.isEmpty() && fresh > 0) {

            int size = q.size();

            // Process all oranges rotten at current minute
            for (int i = 0; i < size; i++) {

                int[] current = q.poll();

                int row = current[0];
                int col = current[1];

                // Check four directions
                for (int d = 0; d < 4; d++) {

                    int newRow = row + dx[d];
                    int newCol = col + dy[d];

                    // Check boundary + fresh orange
                    if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        // Make fresh orange rotten
                        grid[newRow][newCol] = 2;

                        fresh--;

                        // Add newly rotten orange to queue
                        q.offer(new int[]{newRow, newCol});
                    }
                }
            }

            minutes++;
        }

        // Fresh oranges still remaining
        if (fresh > 0) {
            return -1;
        }

        return minutes;
    }
}
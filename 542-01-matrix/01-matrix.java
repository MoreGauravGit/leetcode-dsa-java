import java.util.*;

class Solution {

    public int[][] updateMatrix(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        boolean[][] visited = new boolean[rows][cols];

        int[][] ans = new int[rows][cols];

        // Put all 0s into queue
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (mat[i][j] == 0) {

                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // UP, DOWN, LEFT, RIGHT
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Multi-source BFS
        while (!q.isEmpty()) {

            int[] current = q.poll();

            int x = current[0];
            int y = current[1];

            // Check four directions
            for (int i = 0; i < 4; i++) {

                int newx = x + dx[i];
                int newy = y + dy[i];

                // Check boundaries + not visited
                if (newx >= 0 && newx < rows &&
                    newy >= 0 && newy < cols &&
                    !visited[newx][newy]) {

                    ans[newx][newy] = ans[x][y] + 1;

                    visited[newx][newy] = true;

                    q.offer(new int[]{newx, newy});
                }
            }
        }

        return ans;
    }
}
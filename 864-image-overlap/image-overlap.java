class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Try all possible shifts
        for (int dx = -(n - 1); dx <= n - 1; dx++) {
            for (int dy = -(n - 1); dy <= n - 1; dy++) {
                maxOverlap = Math.max(maxOverlap, overlap(img1, img2, dx, dy));
            }
        }

        return maxOverlap;
    }

    private int overlap(int[][] img1, int[][] img2, int dx, int dy) {
        int n = img1.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = i + dx;
                int y = j + dy;
                if (x >= 0 && x < n && y >= 0 && y < n) {
                    if (img1[i][j] == 1 && img2[x][y] == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

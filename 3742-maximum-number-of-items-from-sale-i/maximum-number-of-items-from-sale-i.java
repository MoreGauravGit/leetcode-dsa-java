
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Calculate free item bonus for each item
        int[] bonus = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i != j &&
                    items[j][0] % items[i][0] == 0) {

                    bonus[i]++;
                }
            }
        }

        // Step 2: DP initialization
        int INF = -1000000000;

        int[] dp = new int[budget + 1];

        for (int i = 1; i <= budget; i++) {
            dp[i] = INF;
        }

        // Step 3: Process each item type
        for (int i = 0; i < n; i++) {

            int price = items[i][1];
            int free = bonus[i];

            int[] next = dp.clone();

            // Process each remainder separately
            for (int r = 0; r < price && r <= budget; r++) {

                int best = INF;

                for (int cost = r, t = 0;
                     cost <= budget;
                     cost += price, t++) {

                    // Consider buying at least one copy
                    if (t > 0) {

                        int prev = cost - price;

                        if (dp[prev] != INF) {
                            best = Math.max(
                                best,
                                dp[prev] - (t - 1)
                            );
                        }
                    }

                    if (best != INF) {

                        int current = free + t + best;

                        next[cost] = Math.max(
                            next[cost],
                            current
                        );
                    }
                }
            }

            dp = next;
        }

        // Step 4: Find maximum answer within budget
        int ans = 0;

        for (int cost = 0; cost <= budget; cost++) {
            ans = Math.max(ans, dp[cost]);
        }

        return ans;
    }
}

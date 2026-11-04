import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void add(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }

        // Find the index of the kth smallest element
        int findKth(int k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0;
                 step >>= 1) {

                int next = idx + step;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= (int) tree[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] values = Arrays.stream(sorted).distinct().toArray();
        int size = values.length;

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(values, nums[i]) + 1;
        }

        Fenwick countBIT = new Fenwick(size);
        Fenwick sumBIT = new Fenwick(size);

        long totalSum = 0;

        // Insert first window
        for (int i = 0; i < x; i++) {
            countBIT.add(rank[i], 1);
            sumBIT.add(rank[i], nums[i]);
            totalSum += nums[i];
        }

        long[] cost = new long[n - x + 1];

        int medianPosition = (x + 1) / 2;

        // Calculate cost for every window of size x
        for (int start = 0; start <= n - x; start++) {

            int medianRank = countBIT.findKth(medianPosition);
            long median = values[medianRank - 1];

            long leftCount = countBIT.query(medianRank);
            long leftSum = sumBIT.query(medianRank);

            long rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            cost[start] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (start < n - x) {

                int removeIndex = start;
                int addIndex = start + x;

                countBIT.add(rank[removeIndex], -1);
                sumBIT.add(rank[removeIndex], -nums[removeIndex]);

                countBIT.add(rank[addIndex], 1);
                sumBIT.add(rank[addIndex], nums[addIndex]);

                totalSum += (long) nums[addIndex] - nums[removeIndex];
            }
        }

        // DP: minimum cost to select j non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Do not select a window ending at i - 1
                curr[i] = curr[i - 1];

                // Select window [i-x, i-1]
                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
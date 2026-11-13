

class Solution {

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;
        int log = 32 - Integer.numberOfLeadingZeros(n);

        // Step 1: Prefix sum
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 2: Find Next Greater Element
        int[] nextGreater = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }

            nextGreater[i] = st.isEmpty() ? n : st.peek();

            st.push(i);
        }

        // Step 3: Binary lifting tables
        int[][] jump = new int[log][n];
        long[][] cost = new long[log][n];

        for (int i = 0; i < n; i++) {

            jump[0][i] = nextGreater[i];

            int end = nextGreater[i] - 1;

            cost[0][i] =
                (long) nums[i] * (end - i + 1)
                - (prefix[end + 1] - prefix[i]);
        }

        // Build binary lifting tables
        for (int p = 1; p < log; p++) {

            for (int i = 0; i < n; i++) {

                int mid = jump[p - 1][i];

                if (mid == n) {
                    jump[p][i] = n;
                    cost[p][i] = cost[p - 1][i];
                } else {
                    jump[p][i] = jump[p - 1][mid];

                    cost[p][i] =
                        cost[p - 1][i] + cost[p - 1][mid];
                }
            }
        }

        // Step 4: Binary search for every starting index
        long answer = 0;

        for (int left = 0; left < n; left++) {

            int low = left;
            int high = n - 1;
            int best = left - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                long operations = getCost(
                    left, mid, nums, prefix, jump, cost, log
                );

                if (operations <= k) {
                    best = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            answer += best - left + 1;
        }

        return answer;
    }

    // Calculate minimum operations for subarray [left, right]
    private long getCost(
        int left,
        int right,
        int[] nums,
        long[] prefix,
        int[][] jump,
        long[][] cost,
        int log
    ) {

        int current = left;
        long operations = 0;

        // Jump over complete maximum-value segments
        for (int p = log - 1; p >= 0; p--) {

            int next = jump[p][current];

            if (next <= right) {

                operations += cost[p][current];
                current = next;
            }
        }

        // Calculate the remaining partial segment
        operations += (long) nums[current] * (right - current + 1)
                    - (prefix[right + 1] - prefix[current]);

        return operations;
    }
}
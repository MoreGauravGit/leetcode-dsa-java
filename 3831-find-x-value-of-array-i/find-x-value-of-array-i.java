class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at previous index
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            // Only remainder matters
            int remainder = num % k;

            // Start a new subarray with current element
            next[remainder]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] > 0) {

                    int newRemainder = (r * remainder) % k;

                    next[newRemainder] += dp[r];
                }
            }

            // Add all subarrays ending at current position
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            // Move to next position
            dp = next;
        }

        return result;
    }
}
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();

        int[] prefix = new int[n + 1];

        // Build prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int minSum = Integer.MAX_VALUE;

        // Try every starting index
        for (int i = 0; i < n; i++) {

            // Try subarray lengths from l to r
            for (int len = l; len <= r; len++) {

                int end = i + len;

                if (end > n) {
                    break;
                }

                int sum = prefix[end] - prefix[i];

                if (sum > 0) {
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
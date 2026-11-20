class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[1_000_003]; // values go up to 10^6 + 1
        int ans = 0;

        for (int num : nums) {
            // use num as num + 1 (must be updated first)
            dp[num + 1] = Math.max(dp[num + 1], dp[num] + 1);
            // use num as is
            dp[num] = Math.max(dp[num], dp[num - 1] + 1);

            ans = Math.max(ans, Math.max(dp[num], dp[num + 1]));
        }

        return ans;
    }
}

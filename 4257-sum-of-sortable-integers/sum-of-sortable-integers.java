class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prevMax = 0;

            for (int start = 0; start < n; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int descents = 0;

                for (int i = start; i < start + k; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);

                    if (i > start && nums[i] < nums[i - 1]) {
                        descents++;
                    }
                }

                if (descents > 1 || (descents == 1 && nums[start] < nums[start + k - 1])) {
                    valid = false;
                    break;
                }

                if (start > 0 && prevMax > min) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}
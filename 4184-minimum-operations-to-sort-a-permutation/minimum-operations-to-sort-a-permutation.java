class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int start = nums[0];

        // Check increasing cyclic order
        boolean increasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (start + i) % n) {
                increasing = false;
                break;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            int rotations = (n - start) % n;

            ans = Math.min(rotations, start + 2);
        }

        // Check decreasing cyclic order
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (start - i + n) % n) {
                decreasing = false;
                break;
            }
        }

        if (decreasing) {
            int rotations = n - 1 - start;

            int operations = Math.min(
                rotations,
                n - rotations
            ) + 1;

            ans = Math.min(ans, operations);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }

        int ans = Integer.MAX_VALUE;

        // Case 1: Rotate the original array
        int p = rotationIndex(nums);

        if (p != -1) {
            if (p == 0) {
                return 0;
            }

            // Direct left rotations, or use two reversals
            ans = Math.min(p, n - p + 2);
        }

        // Case 2: Use one reversal and rotations
        int q = rotationIndex(reversed);

        if (q != -1) {
            ans = Math.min(ans, 1 + Math.min(q, n - q));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int rotationIndex(int[] nums) {
        int n = nums.length;
        int pos = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos = i;
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[(pos + i) % n] != i) {
                return -1;
            }
        }

        return pos;
    }
}

import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<Integer> dq = new ArrayDeque<>();

        long ans = 0;
        long cost = 0;

        int j = n - 1;

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];

            // Merge smaller elements into the current element
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {

                int l = dq.pollLast();

                int r = dq.isEmpty() ? j + 1 : dq.peekLast();

                cost += (long) (r - l) * (num - nums[l]);
            }

            dq.offerLast(i);

            // Shrink window if operations exceed k
            while (cost > k) {

                cost -= nums[dq.peekFirst()] - nums[j];

                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }

                j--;
            }

            // Count valid subarrays ending at j
            ans += j - i + 1;
        }

        return ans;
    }
}
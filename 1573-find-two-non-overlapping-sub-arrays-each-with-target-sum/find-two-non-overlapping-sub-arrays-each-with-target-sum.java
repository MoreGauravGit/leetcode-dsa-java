import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        // best[i] = minimum length of a valid
        // subarray ending at or before i
        int[] best = new int[n];

        Arrays.fill(best, Integer.MAX_VALUE);

        Map<Integer, Integer> map = new HashMap<>();

        // Prefix sum 0 occurs before the array
        map.put(0, -1);

        int prefixSum = 0;
        int answer = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            prefixSum += arr[i];

            // Carry forward previous best
            if (i > 0) {
                best[i] = best[i - 1];
            }

            // Check whether a subarray with sum target exists
            int required = prefixSum - target;

            if (map.containsKey(required)) {

                int start = map.get(required) + 1;

                int currentLength = i - start + 1;

                // Need a previous non-overlapping subarray
                if (start > 0 &&
                    best[start - 1] != Integer.MAX_VALUE) {

                    answer = Math.min(
                        answer,
                        currentLength + best[start - 1]
                    );
                }

                // Update minimum subarray length
                minLength = Math.min(minLength, currentLength);

                best[i] = Math.min(best[i], minLength);
            }

            // Store prefix sum
            map.put(prefixSum, i);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
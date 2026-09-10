import java.util.*;

class Solution {

    public int findMaxLength(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        // Prefix sum 0 exists before the array starts
        map.put(0, -1);

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {

            // Convert 0 into -1
            if (nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }

            // If this prefix sum was seen before
            if (map.containsKey(sum)) {

                int previousIndex = map.get(sum);

                int length = i - previousIndex;

                maxLength = Math.max(maxLength, length);

            } else {

                // Store only first occurrence
                map.put(sum, i);
            }
        }

        return maxLength;
    }
}
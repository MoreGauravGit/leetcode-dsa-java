import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

        int n = nums1.length;
        long k = (long) k1 + k2;

        int[] diff = new int[n];
        int maxDiff = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            total += diff[i];
        }

        if (k >= total) {
            return 0;
        }

        int left = 0, right = maxDiff;

        // Find the smallest level achievable with k operations
        while (left < right) {
            int mid = left + (right - left) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int level = left;
        long required = 0;
        long answer = 0;

        for (int d : diff) {
            if (d > level) {
                required += d - level;
            }

            int finalDiff = Math.min(d, level);
            answer += (long) finalDiff * finalDiff;
        }

        // Remaining operations reduce some differences from level to level - 1
        long remaining = k - required;

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] >= level && level > 0) {
                answer -= (long) level * level;
                answer += (long) (level - 1) * (level - 1);
                remaining--;
            }
        }

        return answer;
    }
}
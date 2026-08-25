import java.util.*;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Store all numbers in a set for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Start checking multiples of k
        int multiple = k;
        while (true) {
            if (!set.contains(multiple)) {
                return multiple;
            }
            multiple += k;
        }
    }
    
    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.missingMultiple(new int[]{8,2,3,4,6}, 2)); // Output: 10
        System.out.println(sol.missingMultiple(new int[]{1,4,7,10,15}, 5)); // Output: 5
    }
}

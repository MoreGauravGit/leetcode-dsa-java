import java.util.*;

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public void generate(int start, int[] nums, List<Integer> current) {

        // Store current subset
        ans.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Skip duplicates
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Take
            current.add(nums[i]);

            // Explore
            generate(i + 1, nums, current);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        generate(0, nums, new ArrayList<>());

        return ans;
    }
}
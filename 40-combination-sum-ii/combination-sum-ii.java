import java.util.*;

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public void generate(int start,
                         int[] candidates,
                         int target,
                         List<Integer> current) {

        // Base Case
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Skip duplicate elements
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // No need to continue if number is greater than target
            if (candidates[i] > target) {
                break;
            }

            // Choose
            current.add(candidates[i]);

            // Explore (use next index because each element can be used only once)
            generate(i + 1, candidates, target - candidates[i], current);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        generate(0, candidates, target, new ArrayList<>());

        return ans;
    }
}
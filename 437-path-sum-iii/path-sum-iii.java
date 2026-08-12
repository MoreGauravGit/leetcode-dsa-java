class Solution {

    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {

        Map<Long, Integer> map = new HashMap<>();

        map.put(0L, 1);

        dfs(root, 0, targetSum, map);

        return count;
    }

    private void dfs(
            TreeNode root,
            long currentSum,
            int target,
            Map<Long, Integer> map) {

        if (root == null) {
            return;
        }

        currentSum += root.val;

        long required = currentSum - target;

        if (map.containsKey(required)) {
            count += map.get(required);
        }

        map.put(
            currentSum,
            map.getOrDefault(currentSum, 0) + 1
        );

        dfs(root.left, currentSum, target, map);

        dfs(root.right, currentSum, target, map);

        map.put(
            currentSum,
            map.get(currentSum) - 1
        );
    }
}
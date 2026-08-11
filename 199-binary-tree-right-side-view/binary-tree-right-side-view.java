class Solution {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {

            int n = q.size();

            for (int i = 0; i < n; i++) {

                TreeNode curr = q.poll();

                // First node of this level = rightmost node
                if (i == 0) {
                    result.add(curr.val);
                }

                // Right first
                if (curr.right != null) {
                    q.offer(curr.right);
                }

                // Left second
                if (curr.left != null) {
                    q.offer(curr.left);
                }
            }
        }

        return result;
    }
}
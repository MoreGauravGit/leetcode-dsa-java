class Pair {

    TreeNode node;
    long index;

    Pair(TreeNode node, long index) {
        this.node = node;
        this.index = index;
    }
}

class Solution {

    public int widthOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));

        long maxWidth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            long first = queue.peek().index;
            long last = first;

            for (int i = 0; i < size; i++) {

                Pair current = queue.poll();

                last = current.index;

                // Left child
                if (current.node.left != null) {
                    queue.offer(
                        new Pair(
                            current.node.left,
                            current.index * 2
                        )
                    );
                }

                // Right child
                if (current.node.right != null) {
                    queue.offer(
                        new Pair(
                            current.node.right,
                            current.index * 2 + 1
                        )
                    );
                }
            }

            long width = last - first + 1;

            maxWidth = Math.max(maxWidth, width);
        }

        return (int) maxWidth;
    }
}
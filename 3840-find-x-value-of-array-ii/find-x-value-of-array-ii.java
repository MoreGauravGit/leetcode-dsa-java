class Solution {

    class Node {
        int[] count;
        int product;

        Node(int k) {
            count = new int[k];
            product = 1 % k;
        }
    }

    int k;
    int n;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, index, value);

            Node result = query(
                    1, 0, n - 1,
                    start, n - 1
            );

            answer[q] = result.count[x];
        }

        return answer;
    }

    private void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].count[rem] = 1;
            tree[node].product = rem;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    private Node merge(Node left, Node right) {

        Node result = new Node(k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            result.count[r] += left.count[r];
        }

        // Prefixes containing all of left
        // and a prefix of right
        for (int r = 0; r < k; r++) {

            int newRemainder =
                    (left.product * r) % k;

            result.count[newRemainder] += right.count[r];
        }

        result.product =
                (left.product * right.product) % k;

        return result;
    }

    private void update(
            int node,
            int left,
            int right,
            int index,
            int value) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].count[rem] = 1;
            tree[node].product = rem;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    private Node query(
            int node,
            int left,
            int right,
            int ql,
            int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(
                    node * 2,
                    left,
                    mid,
                    ql,
                    qr
            );
        }

        if (ql > mid) {
            return query(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    ql,
                    qr
            );
        }

        Node leftResult = query(
                node * 2,
                left,
                mid,
                ql,
                qr
        );

        Node rightResult = query(
                node * 2 + 1,
                mid + 1,
                right,
                ql,
                qr
        );

        return merge(leftResult, rightResult);
    }
}
class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        int[] last = new int[100001];

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // Previous occurrence of x
            int prev = last[x];

            // Add 1 to distinct counts of subarrays
            // ending at i whose start is in [prev, i].
            st.update(prev, i, 1);

            // Sum of squares of distinct counts for subarrays ending at i
            answer = (answer + st.sumSquares()) % MOD;

            last[x] = i + 1;
        }

        return (int) answer;
    }

    static class SegmentTree {
        int n;
        long[] sum;
        long[] squares;
        long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squares = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int left, int right, long val) {
            long len = right - left + 1L;

            squares[node] = (
                squares[node]
                + 2L * val * sum[node]
                + len * val % MOD * val
            ) % MOD;

            sum[node] = (sum[node] + len * val) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;
            apply(node * 2, left, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, right, lazy[node]);
            lazy[node] = 0;
        }

        void update(int ql, int qr, long val) {
            update(1, 0, n - 1, ql, qr, val);
        }

        void update(
            int node, int left, int right,
            int ql, int qr, long val
        ) {
            if (ql <= left && right <= qr) {
                apply(node, left, right, val);
                return;
            }

            push(node, left, right);

            int mid = left + (right - left) / 2;

            if (ql <= mid) {
                update(node * 2, left, mid, ql, qr, val);
            }

            if (qr > mid) {
                update(node * 2 + 1, mid + 1, right, ql, qr, val);
            }

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

            squares[node] = (
                squares[node * 2] + squares[node * 2 + 1]
            ) % MOD;
        }

        long sumSquares() {
            return squares[1];
        }
    }
} 
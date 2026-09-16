class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // We need C(n + k - 1, 2k)
        return (int) nCr(n + k - 1, 2 * k);
    }

    private long nCr(int n, int r) {
        if (r > n) return 0;
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        long numerator = fact[n];
        long denominator = fact[r] * fact[n - r] % MOD;
        return numerator * modInverse(denominator) % MOD;
    }

    private long modInverse(long x) {
        return pow(x, MOD - 2);
    }

    private long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.numberOfSets(4, 2)); // Example output
    }
}

import java.util.*;

class Solution {

    long MOD1 = 1_000_000_007L;
    long MOD2 = 1_000_000_009L;
    long BASE = 100_003L;

    long[] pow1, pow2;
    long[] pref1, pref2;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        buildHash(nums);

        int lo = 1;
        int hi = n;
        int ans = n;

        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;

            if (existsUnique(nums, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private void buildHash(int[] nums) {
        int n = nums.length;

        pow1 = new long[n + 1];
        pow2 = new long[n + 1];
        pref1 = new long[n + 1];
        pref2 = new long[n + 1];

        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * BASE) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE) % MOD2;
        }

        for (int i = 0; i < n; i++) {
            pref1[i + 1] = (pref1[i] * BASE + nums[i]) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE + nums[i]) % MOD2;
        }
    }

    private boolean existsUnique(int[] nums, int len) {
        int n = nums.length;

        HashMap<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            long h1 = getHash1(i, i + len - 1);
            long h2 = getHash2(i, i + len - 1);

            long key = (h1 << 32) ^ h2;

            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

    private long getHash1(int l, int r) {
        long val = (pref1[r + 1]
                - pref1[l] * pow1[r - l + 1]) % MOD1;

        if (val < 0) val += MOD1;

        return val;
    }

    private long getHash2(int l, int r) {
        long val = (pref2[r + 1]
                - pref2[l] * pow2[r - l + 1]) % MOD2;

        if (val < 0) val += MOD2;

        return val;
    }
}
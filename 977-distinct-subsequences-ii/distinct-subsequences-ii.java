class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        
        // dp[i] = number of distinct subsequences using first i characters
        long[] dp = new long[n + 1];
        dp[0] = 1; // empty subsequence
        
        // last occurrence of each character
        int[] last = new int[26];
        Arrays.fill(last, -1);
        
        for (int i = 1; i <= n; i++) {
            int c = s.charAt(i - 1) - 'a';
            
            // double the subsequences (include or exclude current char)
            dp[i] = (dp[i - 1] * 2) % MOD;
            
            // subtract duplicates if char appeared before
            if (last[c] != -1) {
                dp[i] = (dp[i] - dp[last[c] - 1] + MOD) % MOD;
            }
            
            last[c] = i;
        }
        
        // subtract empty subsequence
        return (int)((dp[n] - 1 + MOD) % MOD);
    }
}

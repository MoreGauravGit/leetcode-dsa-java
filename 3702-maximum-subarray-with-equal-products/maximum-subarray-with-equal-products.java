class Solution {

    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {

            long prod = 1;
            long lcmVal = 1;
            int gcdVal = 0;

            for (int j = i; j < n; j++) {

                prod *= nums[j];

                if (gcdVal == 0) {
                    gcdVal = nums[j];
                } else {
                    gcdVal = gcd(gcdVal, nums[j]);
                }

                lcmVal = lcm(lcmVal, nums[j]);

                if (prod == lcmVal * gcdVal) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd((int)a, (int)b) * b;
    }
}
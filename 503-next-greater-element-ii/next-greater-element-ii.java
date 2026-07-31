class Solution {

    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Traverse twice from right to left
        for (int i = 2 * n - 1; i >= 0; i--) {

            int idx = i % n;

            while (!stack.isEmpty() && stack.peek() <= nums[idx]) {
                stack.pop();
            }

            // Fill answers only during the first pass
            if (i < n) {
                if (stack.isEmpty()) {
                    ans[idx] = -1;
                } else {
                    ans[idx] = stack.peek();
                }
            }

            stack.push(nums[idx]);
        }

        return ans;
    }
}
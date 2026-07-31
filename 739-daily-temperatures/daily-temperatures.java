class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Remove all temperatures that are
            // smaller than or equal to current temperature
            while (!stack.isEmpty() &&
                   temperatures[stack.peek()] <= temperatures[i]) {

                stack.pop();
            }

            // If stack is empty, no warmer day exists
            if (stack.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = stack.peek() - i;
            }

            // Push current index
            stack.push(i);
        }

        return ans;
    }
}
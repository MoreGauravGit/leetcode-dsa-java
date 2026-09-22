class Solution {
    public int[] leftRightDifference(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        // Calculate total sum
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < n; i++) {

            // Sum of elements to the right
            int rightSum = totalSum - leftSum - nums[i];

            // Difference
            answer[i] = Math.abs(leftSum - rightSum);

            // Add current element to left sum
            leftSum += nums[i];
        }

        return answer;
    }
}
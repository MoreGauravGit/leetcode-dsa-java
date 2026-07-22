class Solution {

    // Check if all packages can be shipped within 'days'
    // using the given ship capacity.
    public boolean isPossible(int[] weights, int days, int capacity) {

        int currentWeight = 0;
        int requiredDays = 1;

        for (int weight : weights) {

            // If adding this package exceeds capacity,
            // start a new day.
            if (currentWeight + weight > capacity) {
                requiredDays++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }

        }

        return requiredDays <= days;
    }

    // Find the maximum weight (minimum possible capacity)
    public int maxWeight(int[] weights) {

        int max = weights[0];

        for (int weight : weights) {
            max = Math.max(max, weight);
        }

        return max;
    }

    // Find the sum of all weights (maximum possible capacity)
    public int totalWeight(int[] weights) {

        int sum = 0;

        for (int weight : weights) {
            sum += weight;
        }

        return sum;
    }

    public int shipWithinDays(int[] weights, int days) {

        int left = maxWeight(weights);
        int right = totalWeight(weights);

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (isPossible(weights, days, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
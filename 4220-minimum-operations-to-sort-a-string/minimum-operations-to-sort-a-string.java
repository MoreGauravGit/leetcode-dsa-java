
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        boolean sorted = true;
        char min = arr[0];
        char max = arr[0];

        for (int i = 0; i < n; i++) {
            min = (char) Math.min(min, arr[i]);
            max = (char) Math.max(max, arr[i]);

            if (i > 0 && arr[i] < arr[i - 1]) {
                sorted = false;
            }
        }

        // Case 1: Already sorted
        if (sorted) {
            return 0;
        }

        // Case 2: Cannot sort an unsorted string of length 2
        if (n == 2) {
            return -1;
        }

        // Case 3: One operation is enough
        if (arr[0] == min || arr[n - 1] == max) {
            return 1;
        }

        // Case 4: Minimum or maximum occurs in the middle
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] == min || arr[i] == max) {
                return 2;
            }
        }

        // Case 5: Minimum and maximum are both at the wrong ends
        return 3;
    }
}

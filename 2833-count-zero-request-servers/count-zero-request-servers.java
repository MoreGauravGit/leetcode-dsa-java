

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store queries with their original indices
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int left = 0, right = 0;
        
        // Frequency of requests per server
        int[] freq = new int[n + 1];

        // Number of servers receiving requests
        int active = 0;

        for (int i = 0; i < m; i++) {

            int end = q[i][0];
            int start = end - x;

            // Add logs within the right boundary
            while (right < logs.length && logs[right][1] <= end) {

                int id = logs[right][0];

                if (freq[id] == 0) {
                    active++;
                }

                freq[id]++;
                right++;
            }

            // Remove logs before the left boundary
            while (left < right && logs[left][1] < start) {

                int id = logs[left][0];

                freq[id]--;

                if (freq[id] == 0) {
                    active--;
                }

                left++;
            }

            // Servers with zero requests
            ans[q[i][1]] = n - active;
        }

        return ans;
    }
}

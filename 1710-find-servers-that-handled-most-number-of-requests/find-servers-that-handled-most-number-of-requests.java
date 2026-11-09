
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        int n = arrival.length;

        // Store available server IDs in sorted order
        TreeSet<Integer> available = new TreeSet<>();

        // Store busy servers: [completionTime, serverId]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Request count for each server
        int[] count = new int[k];

        // Initially all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < n; i++) {

            int time = arrival[i];

            // Release servers whose tasks are completed
            while (!busy.isEmpty() && busy.peek()[0] <= time) {

                long[] server = busy.poll();

                available.add((int) server[1]);
            }

            // If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find next available server >= preferred
            Integer server = available.ceiling(preferred);

            // If not found, wrap around to smallest available ID
            if (server == null) {
                server = available.first();
            }

            // Assign request to this server
            available.remove(server);

            count[server]++;

            // Calculate completion time
            busy.offer(new long[]{
                (long) arrival[i] + load[i],
                server
            });
        }

        // Find maximum number of requests handled
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect all busiest servers
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}

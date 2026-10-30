import java.util.*;

class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build directed graph
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][count] = minimum cost to reach node
        // with count consecutive identical characters
        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Priority Queue: {cost, node, consecutiveCount}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            long cost = curr[0];
            int node = (int) curr[1];
            int count = (int) curr[2];

            // Ignore outdated states
            if (cost != dist[node][count]) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return (int) cost;
            }

            for (int[] edge : adj[node]) {

                int next = edge[0];
                int weight = edge[1];

                int nextCount;

                if (labels.charAt(node) == labels.charAt(next)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // More than k consecutive characters is invalid
                if (nextCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                if (newCost < dist[next][nextCount]) {

                    dist[next][nextCount] = newCost;

                    pq.offer(new long[]{
                        newCost,
                        next,
                        nextCount
                    });
                }
            }
        }

        return -1;
    }
}
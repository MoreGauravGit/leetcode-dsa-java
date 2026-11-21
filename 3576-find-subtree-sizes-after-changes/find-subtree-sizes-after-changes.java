
import java.util.*;

class Solution {
    int[] last;
    int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        // Store the latest ancestor for each character
        last = new int[26];
        Arrays.fill(last, -1);

        int[] newParent = parent.clone();

        // Find new parents using original tree
        dfs(0, adj, s, newParent);

        // Build modified tree
        List<List<Integer>> newAdj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // Calculate subtree sizes
        ans = new int[n];
        calculateSize(0, newAdj);

        return ans;
    }

    void dfs(int node, List<List<Integer>> adj,
             String s, int[] newParent) {

        int ch = s.charAt(node) - 'a';

        int previous = last[ch];

        // Update parent if matching ancestor exists
        if (previous != -1) {
            newParent[node] = previous;
        }

        last[ch] = node;

        for (int child : adj.get(node)) {
            dfs(child, adj, s, newParent);
        }

        // Restore previous ancestor
        last[ch] = previous;
    }

    int calculateSize(int node, List<List<Integer>> adj) {
        int size = 1;

        for (int child : adj.get(node)) {
            size += calculateSize(child, adj);
        }

        ans[node] = size;

        return size;
    }
}

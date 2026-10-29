import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth, tin, tout, parent;
    private int[] bit;
    private int timer;
    private int[] mask;
    private char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        chars = s.toCharArray();
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        mask = new int[n];
        bit = new int[n + 2];
        timer = 0;

        dfs(0, -1, 0);

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        for (int i = 0; i < n; i++) {
            int value = 1 << (chars[i] - 'a');
            add(tin[i], value);
            add(tout[i] + 1, value);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');

                if (oldMask != newMask) {
                    add(tin[u], oldMask ^ newMask);
                    add(tout[u] + 1, oldMask ^ newMask);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int pathMask = prefix(tin[u])
                             ^ prefix(tin[v])
                             ^ (1 << (chars[lca] - 'a'));

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private void dfs(int u, int p, int d) {
        parent[u] = (p == -1) ? 0 : p;
        depth[u] = d;
        tin[u] = ++timer;

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }

        tout[u] = timer;
    }

    private void add(int i, int value) {
        while (i < bit.length) {
            bit[i] ^= value;
            i += i & -i;
        }
    }

    private int prefix(int i) {
        int result = 0;
        while (i > 0) {
            result ^= bit[i];
            i -= i & -i;
        }
        return result;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; diff > 0; j++, diff >>= 1) {
            if ((diff & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }
}
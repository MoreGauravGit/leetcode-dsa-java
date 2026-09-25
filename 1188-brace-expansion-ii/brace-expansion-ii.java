import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parse(String expr) {
        List<Set<String>> parts = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '{') {
                int j = i, balance = 0;
                do {
                    if (expr.charAt(j) == '{') balance++;
                    if (expr.charAt(j) == '}') balance--;
                    j++;
                } while (balance > 0);
                // recursive parse inside braces
                Set<String> inner = union(expr.substring(i + 1, j - 1));
                parts.add(inner);
                i = j;
            } else {
                // single letter
                parts.add(new HashSet<>(Arrays.asList("" + expr.charAt(i))));
                i++;
            }
        }
        // concatenate all parts
        return concat(parts);
    }

    private Set<String> union(String expr) {
        Set<String> res = new HashSet<>();
        int i = 0, balance = 0, start = 0;
        for (; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '{') balance++;
            if (c == '}') balance--;
            if (c == ',' && balance == 0) {
                res.addAll(parse(expr.substring(start, i)));
                start = i + 1;
            }
        }
        res.addAll(parse(expr.substring(start)));
        return res;
    }

    private Set<String> concat(List<Set<String>> parts) {
        Set<String> res = new HashSet<>();
        res.add(""); // start with empty string
        for (Set<String> part : parts) {
            Set<String> newRes = new HashSet<>();
            for (String prefix : res) {
                for (String suffix : part) {
                    newRes.add(prefix + suffix);
                }
            }
            res = newRes;
        }
        return res;
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.braceExpansionII("{a,b}{c,{d,e}}"));
        // Output: [ac, ad, ae, bc, bd, be]
    }
}

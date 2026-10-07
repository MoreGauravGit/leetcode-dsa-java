import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // Check whether current string is valid
                if (isValid(current)) {
                    result.add(current);
                    found = true;
                }

                // If valid strings are found at this level,
                // don't generate strings with more removals.
                if (found) {
                    continue;
                }

                // Remove one parenthesis
                for (int j = 0; j < current.length(); j++) {

                    char ch = current.charAt(j);

                    // We only remove parentheses
                    if (ch != '(' && ch != ')') {
                        continue;
                    }

                    String next =
                            current.substring(0, j)
                            + current.substring(j + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            // Minimum-removal valid strings found
            if (found) {
                break;
            }
        }

        return result;
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            }
            else if (ch == ')') {

                balance--;

                // More ')' than '('
                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}
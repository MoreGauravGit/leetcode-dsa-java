import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // base score

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // start new frame
            } else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }

   
}

import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder()); // base builder

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder()); // start new segment
            } else if (c == ')') {
                StringBuilder segment = stack.pop();
                segment.reverse();
                stack.peek().append(segment); // append reversed to previous
            } else {
                stack.peek().append(c); // normal char
            }
        }

        return stack.pop().toString();
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverseParentheses("(abcd)")); 
        // Output: dcba
        System.out.println(sol.reverseParentheses("(u(love)i)")); 
        // Output: iloveu
        System.out.println(sol.reverseParentheses("(ed(et(oc))el)")); 
        // Output: leetcode
    }
}

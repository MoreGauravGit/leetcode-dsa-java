class Solution {
    public boolean checkValidString(String s) {
        int low = 0;   // minimum possible open parentheses
        int high = 0;  // maximum possible open parentheses

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                if (low > 0) low--;  // use one '(' if possible
                high--;
            } else { // '*'
                if (low > 0) low--;  // treat '*' as ')'
                high++;              // treat '*' as '('
            }

            if (high < 0) return false; // too many ')'
        }

        return low == 0;
    }

   
}

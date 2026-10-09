class Solution {
    public int minInsertions(String s) {
        int open = 0;       // count of unmatched '('
        int insertions = 0; // total insertions needed

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else { // c == ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // we have a "))" pair
                    i++; // skip next ')'
                } else {
                    // single ')', need one more ')'
                    insertions++;
                }

                if (open > 0) {
                    open--; // match with a '('
                } else {
                    // no '(' to match, need to insert one
                    insertions++;
                }
            }
        }

        // each unmatched '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }

  
}

class Solution {
    public int[] maxDepthAfterSplit(String seq) {

        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            char ch = seq.charAt(i);

            if (ch == '(') {
                depth++;

                // Odd depth -> group 1
                // Even depth -> group 0
                answer[i] = depth % 2;
            } 
            else {
                // Use current depth before decreasing
                answer[i] = depth % 2;

                depth--;
            }
        }

        return answer;
    }
}
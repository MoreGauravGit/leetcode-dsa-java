class Solution {
    public int reverseDegree(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            int reversePosition = 'z' - ch + 1;
            int stringPosition = i + 1;

            answer += reversePosition * stringPosition;
        }

        return answer;
    }
}
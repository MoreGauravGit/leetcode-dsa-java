class Solution {
    public long countCommas(long n) {

        long answer = 0;

        // First number that needs a comma
        long start = 1000;

        while (start <= n) {

            answer += n - start + 1;

            // Next comma position
            start *= 1000;
        }

        return answer;
    }
}
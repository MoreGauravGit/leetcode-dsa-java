import java.util.*;

class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by ending position
        Arrays.sort(arr, (a, b) -> {
            if (a.end != b.end) {
                return Integer.compare(a.end, b.end);
            }
            return Integer.compare(a.index, b.index);
        });

        // Store ending positions for binary search
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            ends[i] = arr[i].end;
        }

        /*
         * prev[i] =
         * last interval whose end < arr[i].start
         */
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(ends, i, arr[i].start);
        }

        /*
         * dp[i][k]
         *
         * Best result using first i intervals
         * and selecting at most k intervals.
         *
         * dp[i][k] stores the selected original indices.
         */

        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {

            int current = i - 1;

            for (int k = 0; k <= 4; k++) {

                // Option 1: Skip current interval
                dp[i][k] = dp[i - 1][k];

                // Option 2: Take current interval
                if (k > 0) {

                    int previousIndex = prev[current];

                    State previous =
                        dp[previousIndex + 1][k - 1];

                    long newScore =
                        previous.score + arr[current].weight;

                    List<Integer> newIndices =
                        new ArrayList<>(previous.indices);

                    newIndices.add(arr[current].index);

                    Collections.sort(newIndices);

                    State candidate =
                        new State(newScore, newIndices);

                    if (isBetter(candidate, dp[i][k])) {
                        dp[i][k] = candidate;
                    }
                }
            }
        }

        State answer = dp[n][4];

        return answer.indices
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }


    // Binary search:
    // Find rightmost index j < current
    // such that ends[j] < start

    private int findPrevious(
        int[] ends,
        int current,
        int start
    ) {

        int left = 0;
        int right = current - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (ends[mid] < start) {

                answer = mid;

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }


    static class State {

        long score;

        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }


    private boolean isBetter(
        State candidate,
        State current
    ) {

        // Higher score is always better
        if (candidate.score != current.score) {
            return candidate.score > current.score;
        }

        // Same score:
        // lexicographically smaller indices win
        return lexicographicallySmaller(
            candidate.indices,
            current.indices
        );
    }


    private boolean lexicographicallySmaller(
        List<Integer> a,
        List<Integer> b
    ) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}
import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Build map
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        // Step 2: Parse string
        StringBuilder result = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder();
        boolean insideBracket = false;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBracket = true;
                keyBuilder.setLength(0); // reset
            } else if (c == ')') {
                insideBracket = false;
                String key = keyBuilder.toString();
                result.append(map.getOrDefault(key, "?"));
            } else {
                if (insideBracket) {
                    keyBuilder.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> knowledge = Arrays.asList(
            Arrays.asList("name","bob"),
            Arrays.asList("age","two")
        );
        System.out.println(sol.evaluate("(name)is(age)yearsold", knowledge));
        // Output: bobistwoyearsold
    }
}

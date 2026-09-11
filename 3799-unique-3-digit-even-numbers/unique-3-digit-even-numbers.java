class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();
        int n = digits.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // Ensure distinct indices
                    if (i == j || j == k || i == k) continue;
                    
                    int d1 = digits[i]; // hundreds
                    int d2 = digits[j]; // tens
                    int d3 = digits[k]; // units
                    
                    // Conditions: no leading zero, last digit even
                    if (d1 != 0 && d3 % 2 == 0) {
                        int num = d1 * 100 + d2 * 10 + d3;
                        set.add(num);
                    }
                }
            }
        }
        
        return set.size();
    }}
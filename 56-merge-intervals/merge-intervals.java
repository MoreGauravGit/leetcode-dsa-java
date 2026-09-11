class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]) ; 

        int[] curr = intervals[0] ; 

        for ( int i = 1 ;  i < intervals.length ;i++){
            int[] next = intervals[i] ; 

            if (next[0] <= curr[1]){
                curr[1] = Math.max(curr[1],next[1]) ; 
            }
            else {
                ans.add(curr) ; 
                curr = next ; 
            }

            

          
        }
        ans.add(curr) ; 
      
          return ans.toArray(new int[ans.size()][]) ; 
    }
}
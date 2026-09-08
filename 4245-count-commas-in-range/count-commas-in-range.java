class Solution { 
    public int countCommas(long n) { 
        int total = 0; 
        long start = 1000; 
        int commas = 1; 
 
        while (start <= n) { 
            long end = start * 1000 - 1; 
            long count = Math.min(n, end) - start + 1; 
            total += (int)(count * commas); 
            start *= 1000; 
            commas++; 
        } 
 
        return total; 
    } 
}

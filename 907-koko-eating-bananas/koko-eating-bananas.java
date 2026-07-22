class Solution {

    public static boolean canFinish(int[] arr, int h, int speed ){
        long total_hrs = 0 ; 

        for (int num : arr){
            total_hrs += (num + speed -1 )/speed ; 
        }

        return total_hrs <= h ;
    }

    public static int maxPiles(int[] arr){
        int max_piles = Integer.MIN_VALUE ;
        for (int num  :arr){
            max_piles = Math.max (max_piles , num ) ; 
        }

        return max_piles ; 
    }
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1 ; 
        int right = maxPiles(piles) ; 

        while(left <= right ){
            int mid = left + (right - left) / 2 ; 

            if (canFinish(piles , h , mid ) ) {
                right = mid - 1 ; 
            } 
            else {
                left = mid + 1 ; 
            }

        }
        return left   ; 
        
    }
}
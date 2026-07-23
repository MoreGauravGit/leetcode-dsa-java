class Solution {

    public static int[] maxMinInt (int[] arr){
        int maxi = arr[0] ; 
        int mini = arr[0] ; 

        for (int i: arr){
            maxi = Math.max(i,maxi) ; 
            mini = Math.min(i,mini) ; 
        }
        return new int[] {maxi,mini}  ; 
    }

    public static boolean isPossible(int[] arr , int m ,int k,int days){
        int flower = 0 ; 
        int bouke = 0 ;
        for (int bloom : arr){
            if (bloom <= days){
                flower++ ; 

                if (flower == k ){
                    bouke ++ ; 
                    flower = 0 ; 
                }
            }
            else {
                flower = 0 ; 
            }
        }
        return bouke >= m ; 
    }

    public int minDays(int[] bloomDay, int m, int k) {
        if ((long)m*k > bloomDay.length ) return -1 ; 

        int[] range = maxMinInt(bloomDay) ; 
        int right = range[0] ; 
        int left = range[1] ; 

        while (left <= right ){
            int mid = left + (right - left )/2 ; 

            if (isPossible(bloomDay , m,k,mid)) {
                right = mid  -1 ;  
            } 
            else {
                left = mid + 1 ; 
            }
        }
        return left ;
 
    }
}
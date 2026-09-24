class Solution {
    public int[] max_two(int num ){
        int max1 = 0 ;
        int max2 = 0; 

        while(num > 0 ){
            int rem = num %10 ; 
            if (rem > max1 ){
                max2 = max1 ; 
                max1 = rem ;
                
            } else if (rem > max2 && rem<=max1){
                max2 = rem ; 
            }

            num /= 10 ; 
        }

        return new int[]{max1, max2};
    }

    public int maxProduct(int n) {
        int[] arr = max_two(n) ; 

        return arr[0]*arr[1] ; 
    }
}
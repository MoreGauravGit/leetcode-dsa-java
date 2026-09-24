class Solution {

    public static int get_sum(int num ){
        int sum = 0 ; 
        while(num > 0){
            int rem = num % 10 ; 
            sum += rem ;
            num /= 10 ; 
        }

        return sum ; 
    }
    public int smallestIndex(int[] nums) {
        int sum = 0 ; 
        for (int i = 0 ; i < nums.length ; i++){
            if (get_sum(nums[i]) == i){
                return i ; 
            }

        }

        return -1 ; 
    }
}
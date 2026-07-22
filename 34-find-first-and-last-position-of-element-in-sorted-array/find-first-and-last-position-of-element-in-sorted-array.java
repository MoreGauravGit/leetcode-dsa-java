class Solution {

     public static int search (int[] arr , int target , boolean  rightptr){
        int right = arr.length  -1 ; 
        int left = 0 ; 
        int ans = -1 ; 

        while (left  <= right ){

            int mid = left + (right - left )/2 ; 
            if (arr[mid] == target){
                ans = mid ; 
                if (rightptr ){
                    left = mid+1 ; 
                }
                else{
                    right = mid - 1 ; 
                }
            }

            else if (arr[mid] > target ){
                right = mid -1 ; 
            }
            else {
                left = mid + 1 ; 
            }
        }   
        return ans ;      
    }
    public int[] searchRange(int[] nums, int target) {
            return new int[] {search(nums,target,false) ,search(nums,target,true)};
    }
}
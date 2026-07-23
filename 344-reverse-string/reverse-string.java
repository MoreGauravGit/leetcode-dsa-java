class Solution {

    public void reverse(char[] a , int left , int right ){
        if (right <= left )return ; 

        char temp = a[right] ; 
        a[right] = a[left] ; 
        a[left] = temp ; 

        reverse(a , left + 1 , right - 1 ) ; 
    }
    public void reverseString(char[] s) {
        reverse (s , 0 , s.length - 1  ) ; 
    }
}
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder() ; 
        int depth =  0 ; 
        for (char c : s.toCharArray()){
            if (c == '('){
                if (depth > 0 ){
                    ans.append(c) ;
                }//inner if 1st
                depth ++ ;
            }else{
                depth-- ;
                if (depth > 0 ){
                    ans.append(c) ;
                }
            }
        }//for loop
        return ans.toString() ;
    }
}
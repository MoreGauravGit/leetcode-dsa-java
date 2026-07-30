class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder()  ;
        Stack<Character> stack = new Stack<>() ; 
        for(char c : s.toCharArray()){
           if (stack.isEmpty()){
            stack.push(c);
            sb.append(c) ; 
           } else{
               

                if (stack.peek() == c){
                    sb.deleteCharAt(sb.length() - 1) ;
                     stack.pop() ;
                }else{
                stack.push(c);
                sb.append(c) ;  
                }
           }
                
            
          
            
        }
         return sb.toString();
    }
}
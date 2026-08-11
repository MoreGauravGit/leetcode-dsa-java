/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int diameter = 0 ;  
    public int diameterOfBinaryTree(TreeNode root) {
        height(root); 
        return diameter ; 
    }

    public int  height(TreeNode root){
        if (root == null){
            return 0 ; 
        }

        int right = height(root.right) ; 
        int left = height(root.left) ; 
        diameter = Math.max(diameter,right+left) ; 

        return 1+Math.max(right, left) ; 
        
    }
}
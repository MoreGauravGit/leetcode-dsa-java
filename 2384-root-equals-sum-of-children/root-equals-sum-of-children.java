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

    public int sum_of_node(TreeNode root){
        if (root == null){
            return 0 ; 
        }
        TreeNode left = root.left ;
        TreeNode right = root.right ; 

        return( root.val  + sum_of_node(left) + sum_of_node(right) ); 
    }
    public boolean checkTree(TreeNode root) {
        int root_val = root.val ; 
        int obtained = sum_of_node(root) - root_val ; 
        return root_val == obtained ; 
    }
}
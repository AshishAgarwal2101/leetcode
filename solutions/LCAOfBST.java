/**
 *
 * 235. Lowest Common Ancestor of a Binary Search Tree::::::::::::::
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * 
 *  
 * Example 1:
 * 
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * 
 * 
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * 
 * Example 3:
 * 
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 * 
*/



import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    /* --------------Solution 1: Common to normal binary tree solution -----------------
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //assuming that p and q are definitely present
        
        if(root == p || root == q || root == null){
            return root; //p and q are either "LCA" or "descendant of LCA"
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!= null && right != null){
            return root; //root becomes LCA since p and q on either sides
        }
        if(left != null){
            return left; //LCA on left side
        }
        return right; //LCA on right side
    }
    */
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val<root.val && q.val<root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(p.val>root.val && q.val>root.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        
        return root;
    }
}

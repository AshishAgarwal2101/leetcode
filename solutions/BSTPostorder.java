/**
 *
 * 145. Binary Tree Postorder Traversal::::::::::::
 * 
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * 
 * Example 1:
 * 
 * 
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * 
 * Example 2:
 * 
 * Input: root = []
 * Output: []
 * 
 * Example 3:
 * 
 * Input: root = [1]
 * Output: [1]
 *  
 * Constraints:
 * 
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *  
 * 
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * 
*/



import java.util.*;

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
    List<Integer> res = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return res;
    }
    
    public void postorder(TreeNode root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        res.add(root.val);
    }
}

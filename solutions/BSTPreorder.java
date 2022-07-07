/**
 *
 * 144. Binary Tree Preorder Traversal:::::::
 * 
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * 
 * 
 * Example 1:
 * 
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
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
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 100].
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
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return res;
    }
    
    public void preorder(TreeNode root){
        if(root == null){
            return;
        }
        res.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}

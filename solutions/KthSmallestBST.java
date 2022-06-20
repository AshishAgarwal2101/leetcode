/**
 *
 * 230. Kth Smallest Element in a BST::::::
 * 
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * 
 * Example 2:
 * 
 * 
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *  
 * 
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
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
    /* -----------Solution 1-----------------
    TreeNode kNode = null;
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestUtil(root, k);
        return kNode.val;
    }
    
    public int kthSmallestUtil(TreeNode root, int k){
        //return value = number of nodes
        if(root == null){
            return 0;
        }
        if(k <= 0){
            return -1;
        }
        
        int left = kthSmallestUtil(root.left, k);
        if(left == -1){
            return -1;
        }
        if(left == k-1){
            kNode = root;
            return -1;
        }
        
        int right = kthSmallestUtil(root.right, k-(left+1));
        if(right == -1){
            return -1;
        }
        
        return (left+right+1);
    }
    */
    
    /* -----------Solution 2 --------------*/
    public int kthSmallest(TreeNode root, int k){
        List<TreeNode> orderedList = new ArrayList<>();
        inorder(orderedList, root, k);
        return orderedList.get(k-1).val; //there is still a chance that number of nodes in orderedList is greater than k
    }
    
    public void inorder(List<TreeNode> list, TreeNode root, int k){
        if(root == null){
            return;
        }
        if(list.size() == k){
            return;
        }
        
        inorder(list, root.left, k);
        list.add(root);
        inorder(list, root.right, k);
    }
}

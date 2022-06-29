/**
 *
 * 124. Binary Tree Maximum Path Sum:::::::::
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * 
 * The path sum of a path is the sum of the node's values in the path.
 * 
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * 
 * Example 2:
 * 
 * 
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
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
    Map<TreeNode, Integer> maxPathSumMap = new HashMap<>(); //contains either (leftPath or rightPath) plus root
    int result = Integer.MIN_VALUE; //final res will have 1 node for all -ve nodes
    public int maxPathSum(TreeNode root) {
        maxPathSumMap.put(null, 0);
        pathSum(root);
        maxPathUtil(root);
        return result;
    }
    
    public void maxPathUtil(TreeNode root){
        if(root == null){
            return;
        }
        
        int left = maxPathSumMap.get(root.left);
        int right = maxPathSumMap.get(root.right);
        
        int localRes = root.val;
        localRes = Math.max(localRes, root.val+left);
        localRes = Math.max(localRes, root.val+right);
        localRes = Math.max(localRes, root.val+left+right);
        
        result = Math.max(result, localRes);
        
        maxPathUtil(root.left);
        maxPathUtil(root.right);
    }
    
    public int pathSum(TreeNode root){ //computes max path sum starting root to either left or right
        int localRes = root.val;
        
        if(root.left == null && root.right == null){
            maxPathSumMap.put(root, localRes);
            return root.val;
        }
        if(root.left != null){
            localRes = Math.max(localRes, root.val+pathSum(root.left));
        }
        if(root.right != null){
            localRes= Math.max(localRes, root.val+pathSum(root.right));
        }
        
        maxPathSumMap.put(root, localRes);
        return localRes;
    }
}

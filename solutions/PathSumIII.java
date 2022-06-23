/**
 *
 * 437. Path Sum III:::::::::
 * 
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * 
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
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
    /* ----------Solution 1------------------
    int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        pathSumUtil(root, targetSum);
        return res;
    }
    
    public void pathSumUtil(TreeNode root, int targetSum){
        if(root == null){
            return;
        }
        
        calculate(root, targetSum); //consider start as root, calculate sum
        pathSumUtil(root.left, targetSum);
        pathSumUtil(root.right, targetSum);
    }
    
    public void calculate(TreeNode node, int remaining){
        //go to all possible paths 'starting' from root and calculate whether target reached
        if(node == null){
            return;
        }
        
        if(remaining == node.val){
            res++;
        }
        
        calculate(node.left, remaining-node.val);
        calculate(node.right, remaining-node.val);
    }
    */
    
    int res =0;
    HashMap<Integer, Integer> map = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null) {
            return res;
        }
        map.put(0,1);
        return helper(root, 0 , targetSum);
        
    }
    
    public int helper(TreeNode root,int curSum, int targetSum){
        if(root==null) {
            return 0;
        }
        curSum += root.val;
        int res = map.getOrDefault(curSum-targetSum,0);
        map.put(curSum,map.getOrDefault(curSum,0)+1);
        res = res + helper(root.left,curSum,targetSum) + helper(root.right,curSum,targetSum);
        map.put(curSum,map.get(curSum)-1);
        return res;
    }
}

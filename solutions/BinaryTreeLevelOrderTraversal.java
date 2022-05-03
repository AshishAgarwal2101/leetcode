/**
 *
 * Binary Tree Level Order Traversal::::
 * 
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 * 
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * 
 * Input: root = []
 * Output: []
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        
        result.add(Arrays.asList(getNodeValue(root)));
        List<TreeNode> nextLevelNodes = getNextLevelNodes(Arrays.asList(root));
        while(nextLevelNodes.size() != 0){
            List<Integer> nextLevelNodeValues = nextLevelNodes.stream()
                .map(this::getNodeValue)
                .collect(Collectors.toList());
            result.add(nextLevelNodeValues);
            nextLevelNodes = getNextLevelNodes(nextLevelNodes);
        }
        
        return result;
    }
    
    public List<TreeNode> getNextLevelNodes(List<TreeNode> currLevelNodes){
        List<TreeNode> nextLevelNodes = new ArrayList<>();
        for(TreeNode node:currLevelNodes){
            if(node.left != null){
                nextLevelNodes.add(node.left);
            }
            if(node.right != null){
                nextLevelNodes.add(node.right);
            }
        }
        
        return nextLevelNodes;
    }
    
    public int getNodeValue(TreeNode node){
        return node.val;
    }
}

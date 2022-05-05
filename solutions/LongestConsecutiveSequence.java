/**
 *
 * 128. Longest Consecutive Sequence:::::
 * 
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * 
 * You must write an algorithm that runs in O(n) time.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 * 
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *  
 * 
 * Constraints:
 * 
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 
*/



import java.util.*;


class HashLinkedNode {
    int val;
    HashLinkedNode right;
    
    public HashLinkedNode(){}
    
    public HashLinkedNode(int val){
        this.val = val;
    }
    
    public HashLinkedNode(int val, HashLinkedNode right){
        this.val = val;
        this.right = right;
    }
}

class Solution {
    HashMap<Integer, HashLinkedNode> roots = new HashMap<>();
    HashMap<Integer, HashLinkedNode> hashNodes = new HashMap<>();
    
    public int longestConsecutive(int[] nums) {
        for(int i=0; i<nums.length; i++){
            createHashLinkedNode(nums[i]);
        }
        
        int maxSize = 0;
        for(int key:roots.keySet()){
            int linkedListSize = getLinkedListSize(roots.get(key));
            if(linkedListSize > maxSize){
                maxSize = linkedListSize;
            }
        }
        
        return maxSize;
    }
    
    public void createHashLinkedNode(int num){
        HashLinkedNode currNode = hashNodes.get(num);
        if(currNode != null){
            return;
        }
        currNode = new HashLinkedNode(num);
        hashNodes.put(num, currNode);
        
        HashLinkedNode leftNode = hashNodes.get(num-1);
        HashLinkedNode rightNode = hashNodes.get(num+1);
        
        if(leftNode != null && rightNode != null){
            leftNode.right = currNode;
            currNode.right = rightNode;
            roots.remove(num+1);
        }
        else if(leftNode == null && rightNode == null){
            roots.put(num, currNode);
        }
        else if(leftNode != null){
            leftNode.right = currNode;
        }
        else if (rightNode != null){
            currNode.right = rightNode;
            roots.remove(num+1);
            roots.put(num, currNode);
        }
    }
    
    public int getLinkedListSize(HashLinkedNode root){
        int size = 0;
        HashLinkedNode temp = root;
        while(temp != null){
            temp = temp.right;
            size++;
        }
        
        //System.out.println("root: "+root.val+"-----size: "+size);
        return size;
    }
}

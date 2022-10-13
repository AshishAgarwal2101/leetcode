/**
 *
 * 47. Permutations II::::::::::::::::
 * 
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *  
 * Example 2:
 * 
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * 
*/



import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        permuteUniqueUtil(nums, visited, new ArrayList<>());
        return res;
    }
    
    public void permuteUniqueUtil(int[] nums, boolean[] visited, List<Integer> curr){
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr));
        }
        
        for(int i=0; i<nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                curr.add(nums[i]);
                permuteUniqueUtil(nums, visited, curr);
                visited[i] = false;
                curr.remove(curr.size()-1);
                
                while(i<nums.length-1 && nums[i]==nums[i+1]){
                    i++; //same number as prev, so ignore
                }
            }
        }
    }
}

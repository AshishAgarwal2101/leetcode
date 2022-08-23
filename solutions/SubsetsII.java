/**
 *
 * 90. Subsets II::::::::::::::::::;
 * 
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * 
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 
 * Example 2:
 * 
 * Input: nums = [0]
 * Output: [[],[0]]
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * 
*/



import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subsetUtil(nums, 0, new ArrayList<>());
        return res;
    }
    
    /* 
    ---------------Didn't work-----------------
    public void subsetUtil(int[] nums, int i, List<Integer> arr){
        if(i >= nums.length){
            res.add(new ArrayList<>(arr));
            return;
        }
        
        boolean isSameElem = i> 0 && nums[i]==nums[i-1];
        boolean shouldAddCurrElem = arr.size()==0 || (arr.size()>0 && arr.get(arr.size()-1)!=nums[i]);
        if(!(isSameElem && shouldAddCurrElem)){
            arr.add(nums[i]);
            subsetUtil(nums, i+1, arr); //with current element
            arr.remove(arr.size()-1);
        }
        
        subsetUtil(nums, i+1, arr); //without current element
    }
    */
    
    public void subsetUtil(int[] nums, int index, List<Integer> arr){
        res.add(new ArrayList<>(arr));
        
        for(int i=index; i<nums.length; i++){
            if(i > index && nums[i] == nums[i-1]) continue;
            arr.add(nums[i]);
            subsetUtil(nums, i+1, arr);
            arr.remove(arr.size()-1);
        }
    }
}

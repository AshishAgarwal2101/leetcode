/**
 *
 * 300. Longest Increasing Subsequence:::::::
 * 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * 
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * 
 * Example 2:
 * 
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * 
 * Example 3:
 * 
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *  
 * 
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * 
*/



import java.util.*;

class Solution {
    /* ---------Solution 1: Dynamic Programming----------
    Time complexity: O(n^2)
    */
    public int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, 1); //initially, all values are subsequence of just one element
        for(int i=0; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    //max of current value or value till 'j' plus 1 (for  current element)
                    res[i] = Math.max(res[i], res[j]+1);
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<res.length; i++){
            if(res[i] > max){
                max = res[i];
            }
        }
        
        return max;
    }
        
    /*--------------Solution 2: Recursion---------
    Time complexity - TLE
    
    public int lengthOfLIS(int[] nums) {
        return lisUtil(nums, Integer.MIN_VALUE, 0);
    }
    
    public int lisUtil(int[] nums, int minVal, int index){
        if(index >= nums.length){
            return 0;
        }
        
        if(nums[index] >= minVal){
            int next1 = lisUtil(nums, nums[index]+1, index+1); //including curr
            int next2 = lisUtil(nums, minVal, index+1); //without including curr
            return Math.max(next1+1, next2);
        }
        
        return lisUtil(nums, minVal, index+1); //can't inclue curr
    }
    */
}

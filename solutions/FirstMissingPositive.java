/**
 *
 * 41. First Missing Positive:::::::::::::::
 * 
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * 
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,0]
 * Output: 3
 * 
 * Example 2:
 * 
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * 
 * Example 3:
 * 
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * 
*/



import java.util.*;

class Solution {
    /* ------------- Solution 1: O(n) extra space but O(n) time complexity--------------
    public int firstMissingPositive(int[] nums) {
        int smallestPositiveInt = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0 && nums[i] < smallestPositiveInt){
                smallestPositiveInt = nums[i];
            }
        }
        if(smallestPositiveInt > 1){
            return 1;
        }
        
        //position 0 will represent fill of the smallest value and so on; no fill for -ve values
        //smallest value is 1. 
        //Any unfilled index mean that value is not available. Ignore values greater than the length of nums array.
        boolean[] fillValues = new boolean[nums.length+1];
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0 && nums[i] <= nums.length){
                fillValues[nums[i]] = true;
            }
        }
        for(int i=1; i<fillValues.length; i++){
            if(!fillValues[i]) return i;
        }
        
        return fillValues.length;
    }
    */
    
    /*----------------Solution 2: O(1) extra space, O(n) time complexity------------------
    public int firstMissingPositive(int[] nums) {
        int smallestPositiveInt = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0 && nums[i] < smallestPositiveInt){
                smallestPositiveInt = nums[i];
            }
            if(nums[i] < 0){
                nums[i] = 0; //no need for -ve values
            }
        }
        if(smallestPositiveInt > 1){
            return 1;
        }
        
        //position 0 will represent fill of the smallest value and so on; no fill for -ve values
        //smallest value is 1. 
        //Any unfilled index mean that value is not available. Ignore values greater than the length of nums array.
        for(int i=0; i<nums.length; i++){
            int numVal = Math.abs(nums[i]);
            if(numVal > 0 && numVal <= nums.length){
                if(nums[numVal-1] == 0) nums[numVal-1] = -1; //fill for smallest value by default
                else nums[numVal-1] = -1 * Math.abs(nums[numVal-1]);
            }
        }
        for(int i=0; i<nums.length; i++){
            if(nums[i] >= 0) return (i+1);
        }
        
        return (nums.length+1);
    }
    */
    
    /* ---------------------------Solution 3: O(1) extra space, O(n) time complexity---------------*/
    public int firstMissingPositive(int[] nums) {
        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]){
                //with below, index "j-1" would have value "j", i.e., index "j" would have value "j+1".
                swap(nums, i, nums[i]-1); 
                i--; //index 'i' has a new value, reprocess it.
            }
        }
        for(int i=0; i<nums.length; i++){
            if(nums[i] != (i+1)) return (i+1);
        }
        
        return (nums.length+1);
    }
    
    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/**
 *
 * 1567. Maximum Length of Subarray With Positive Product:::::::::::;
 * 
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 * 
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 * 
 * Return the maximum length of a subarray with positive product.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * 
 * Example 2:
 * 
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * 
 * Example 3:
 * 
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 
*/



import java.util.*;

class Solution {
    /*-------------Solution 1---------------
    public int getMaxLen(int[] nums) {
        long currMax = nums[0]  > 0 ? 1 : (nums[0] == 0 ? 0 : -1);
        int currMaxStartIndex = 0;
        int globalMaxLen = nums[0] > 0 ? 1 : 0;

        //left pass
        for(int i=1; i<nums.length; i++){
            if(currMax == 0) {
                currMax = nums[i];
                currMaxStartIndex = i;
            }
            else {
                if(nums[i] == 0) currMax = 0;
                else if(nums[i] < 0) currMax *= -1;
            }
            if(currMax > 0) globalMaxLen = Math.max(globalMaxLen, i-currMaxStartIndex+1);
        }

        
        //right pass
        currMax = nums[nums.length-1]  > 0 ? 1 : (nums[nums.length-1] == 0 ? 0 : -1);
        currMaxStartIndex = nums.length-1;
        globalMaxLen = nums[nums.length-1] > 0 ? Math.max(globalMaxLen, 1) : globalMaxLen;
        for(int i=nums.length-2; i>=0; i--){
            if(currMax == 0) {
                currMax = nums[i];
                currMaxStartIndex = i;
            }
            else {
                if(nums[i] == 0) currMax = 0;
                else if(nums[i] < 0) currMax *= -1;
            }
            if(currMax > 0) globalMaxLen = Math.max(globalMaxLen, currMaxStartIndex-i+1);
        }
        

        return globalMaxLen;
    }
    */

    /* ------------Solution 2: Maintaining lengths-----------*/
    public int getMaxLen(int[] nums) {
        int currPositiveLen = 0;
        int currNegativeLen = 0;
        int globalMaxLen = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                currPositiveLen++;
                currNegativeLen = currNegativeLen > 0 ? currNegativeLen+1 : 0;
            }
            else if(nums[i] < 0){
                int prevPositiveLen = currPositiveLen;
                currPositiveLen = currNegativeLen > 0 ? currNegativeLen+1 : 0;
                currNegativeLen = prevPositiveLen+1;
            }
            else{
                currPositiveLen = 0;
                currNegativeLen = 0;
            }

            globalMaxLen = Math.max(globalMaxLen, currPositiveLen);
        }

        return globalMaxLen;
    }
}

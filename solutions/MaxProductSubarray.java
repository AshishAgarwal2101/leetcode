/**
 *
 * 152. Maximum Product Subarray::::::::::
 * 
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 * 
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * 
 * A subarray is a contiguous subsequence of the array.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * 
 * Example 2:
 * 
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * 
*/



import java.util.*;

class Solution {
    //Two pass approach: Works because subarray starts from one of these:
    //1.Leftmost    2.Rightmost    3.Leftmost after a 0 4.Rightmost before a 0
     public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
         
         int runningProduct = nums[0];
         int globalMax = nums[0];
         for(int i=1; i<nums.length; i++){
             if(runningProduct == 0){
                 runningProduct = nums[i];
             }
             else{
                 runningProduct *= nums[i];
             }
             globalMax = Math.max(globalMax, runningProduct);
         }
         
         runningProduct = nums[nums.length-1];
         globalMax = Math.max(globalMax, runningProduct);
         for(int i=nums.length-2; i>=0; i--){
             if(runningProduct == 0){
                 runningProduct = nums[i];
             }
             else{
                 runningProduct *= nums[i];
             }
             globalMax = Math.max(globalMax, runningProduct);
         }
         
         return globalMax;
    }
}

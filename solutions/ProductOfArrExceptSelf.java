/**
 *
 * 238. Product of Array Except Self::::::::::::
 * 
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * 
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * 
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * Example 2:
 * 
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *  
 * 
 * Constraints:
 * 
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *  
 * 
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 * 
*/



import java.util.*;

class Solution {
    /* ------ Solution 1: O(n) extra space--------
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProducts = new int[len];
        int[] rightProducts = new int[len];
        int[] res = new int[len];
        
        leftProducts[0] = 1;
        rightProducts[len-1] = 1;
        for(int i=1; i<len; i++){
            leftProducts[i] = leftProducts[i-1] * nums[i-1];
        }
        for(int i=len-2; i>=0; i--){
            rightProducts[i] = rightProducts[i+1] * nums[i+1];
        }
        for(int i=0; i<len; i++){
            res[i] = leftProducts[i] * rightProducts[i];
        }
        
        return res;
    }
    */
    
    //Solution 2: O(1) extra space, other than the result
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new  int[len];
        
        //assigning left product
        int prefix = 1;
        for(int i=0; i<nums.length; i++){
            res[i] = prefix;
            prefix = prefix*nums[i];
        }
        
        //aiisgning right product
        prefix = 1;
        for(int i=len-1; i>=0; i--){
            res[i] = prefix*res[i]; //current right product * left product at 'i'
            prefix = prefix*nums[i];
        }
        
        return res;
    }
}

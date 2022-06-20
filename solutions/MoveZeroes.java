/**
 *
 * 283. Move Zeroes:::::::::
 * 
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * 
 * Note that you must do this in-place without making a copy of the array.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Example 2:
 * 
 * Input: nums = [0]
 * Output: [0]
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *  
 * 
 * Follow up: Could you minimize the total number of operations done?
 * 
*/



import java.util.*;

class Solution {
    public void moveZeroes(int[] nums) {
        /*
        ::::::::::::::::::::::::::::First solution
        int lastNZIndex = 0;
        for(int i=0; i<nums.length-1; i++){
            if(nums[i] == 0){
                if(lastNZIndex <= i){
                    lastNZIndex = i+1;
                }
                while(lastNZIndex<nums.length && nums[lastNZIndex]==0){
                    lastNZIndex++;
                }
                if(lastNZIndex == nums.length){
                    break;
                }
                
                int tmp = nums[i];
                nums[i] = nums[lastNZIndex];
                nums[lastNZIndex] = tmp;
            }
        }
        */
        
        int pos = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){ //if not 0, simply like adding new element
                nums[pos++] = nums[i];
            }
        }
        for(int i=pos; i<nums.length; i++){
            nums[i] = 0; //all remaining elements = 0
        }
    }
}

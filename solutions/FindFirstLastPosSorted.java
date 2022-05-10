/**
 *
 * 34. Find First and Last Position of Element in Sorted Array::::::::::;
 * 
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * 
 * If target is not found in the array, return [-1, -1].
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 
 * Example 2:
 * 
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * 
 * Example 3:
 * 
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *  
 * 
 * Constraints:
 * 
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 * 
*/



import java.util.*;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = searchRangeUtil(nums, 0, nums.length-1, target);
        if(res[0] == Integer.MAX_VALUE){
            res[0] = -1;
        }
        
        return res;
    }
    
    public int[] searchRangeUtil(int[] nums, int low, int high, int target){
        if(low > high){
            return new int[]{Integer.MAX_VALUE, -1};
        }
        
        int mid = (low+high)/2;
        if(target > nums[mid]){
            return searchRangeUtil(nums, mid+1, high, target);
        }
        if(target < nums[mid]){
            return searchRangeUtil(nums, low, mid - 1, target);
        }
        else {
            int[] lowerRange = searchRangeUtil(nums, low, mid - 1, target);
            int[] upperRange = searchRangeUtil(nums, mid + 1, high, target);
            int[] res = new int[2];
            res[0] = Math.min(Math.min(lowerRange[0], upperRange[0]), mid);
            res[1] = Math.max(Math.max(lowerRange[1], upperRange[1]), mid);
            
            return res;
        }
    }
}

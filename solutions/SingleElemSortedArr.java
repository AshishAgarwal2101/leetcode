/**
 *
 * 540. Single Element in a Sorted Array
 * 
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * 
 * Return the single element that appears only once.
 * 
 * Your solution must run in O(log n) time and O(1) space.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * 
 * Example 2:
 * 
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 * 
*/



import java.util.*;

class Solution {
    /*----------------O(n) solution---------------
    public int singleNonDuplicate(int[] nums) {
        int res = 0;
        for(int i=0; i<nums.length; i++){
            res ^= nums[i];
        }

        return res;
    }
    */

    /* ----------------O(log n) solution------------*/
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while(left <= right) {
            int mid = (left+right)/2;
            if(isTargetElement(nums, mid)) return nums[mid];

            if(mid%2 == 1){ //if mid is in even position (0-indexed)
                //same element must be to the left to mid
                if(mid == 0 || nums[mid-1] != nums[mid]){
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
            else { //if mid is in odd position (0-indexed)
                //same element must be to the right of mid
                if(mid == nums.length-1 || nums[mid+1] != nums[mid]){
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    public boolean isTargetElement(int[] nums, int pos){
        if(pos !=0 && nums[pos-1] == nums[pos]) return false;
        if(pos != nums.length-1 && nums[pos+1] == nums[pos]) return false;
        return true;
    }
}

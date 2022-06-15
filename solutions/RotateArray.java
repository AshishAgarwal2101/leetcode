/**
 *
 * 189. Rotate Array::::::::::
 * 
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * 
 * Example 2:
 * 
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation: 
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *  
 * 
 * Follow up:
 * 
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * 
*/



import java.util.*;

class Solution {
    /*
    public void rotate(int[] nums, int k) {
        //O(n*k) solution
        //O(1) extra space
        for(int i=0; i<k; i++){
            int temp = nums[nums.length-1];
            for(int j=nums.length-2; j>=0; j--){
                nums[j+1] = nums[j];
            }
            nums[0] = temp;
        }
    }
    */
    
    /*
    public void rotate(int[] nums, int k) {
        //O(n) solution
        //O(n) extra space
        int[] newNums = nums.clone();
        for(int i=0; i<newNums.length; i++){
            int newIndex = (i+k) % newNums.length;
            nums[newIndex] = newNums[i];
        }
    }
    */
    
    public void rotate(int[] nums, int k) {
        //O(n) solution
        //O(1) extra space
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
    
    void reverse(int[] nums, int start, int end){
        int temp = 0;
        while(start < end){
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

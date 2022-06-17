/**
 *
 * 977. Squares of a Sorted Array:::::::::
 * 
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * 
 * Example 2:
 * 
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 *  
 * 
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 * 
*/



import java.util.*;

class Solution {
    public int[] sortedSquares(int[] nums) {
        int pivotIndex = 0; //till pivotIndex, numbers will be negative
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                pivotIndex = i;
            }
            else{
                break;
            }
        }
        
        
        int[] absSortedNums = makeAbsoluteAndMerge(nums, pivotIndex);
        return squareNums(absSortedNums);
    }
    
    public int[] makeAbsoluteAndMerge(int[] nums, int pivotIndex){
        int len = nums.length;
        int i = pivotIndex;
        int j = pivotIndex + 1;
        int k = 0;
        int[] absSortedNums = new int[len];
        
        while(i>=0 && j<len){
            int first = Math.abs(nums[i]);
            int second = nums[j];
            if(first < second){
                absSortedNums[k++] = first;
                i--;
            }
            else{
                absSortedNums[k++] = second;
                j++;
            }
        }
        
        while(i >= 0){
            absSortedNums[k++] = Math.abs(nums[i--]);
        }
        while(j < len){
            absSortedNums[k++] = nums[j++];
        }
        
        return absSortedNums;
    }
    
    public int[] squareNums(int[] nums){
        int[] newNums = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            newNums[i] = nums[i]*nums[i];
        }
        
        return newNums;
    }
}

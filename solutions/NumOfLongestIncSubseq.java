/**
 *
 * 673. Number of Longest Increasing Subsequence:::::::::::::::::
 * 
 * Given an integer array nums, return the number of longest increasing subsequences.
 * 
 * Notice that the sequence has to be strictly increasing.
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 * 
 * Example 2:
 * 
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2000
 * -106 <= nums[i] <= 106
 * 
*/



import java.util.*;

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dpLength = new int[n];
        int[] dpCount = new int[n];
        
        //till any length, at least 1 possible solution
        Arrays.fill(dpLength, 1);
        Arrays.fill(dpCount, 1); 
        
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(nums[i] <= nums[j]) continue;
                
                if(dpLength[j]+1 > dpLength[i]){ //new max length found till length 'i'
                    dpLength[i] = dpLength[j]+1;
                    dpCount[i] = dpCount[j]; //no. of possible solutions till length 'i'
                }
                else if(dpLength[j]+1 == dpLength[i]){ //'j' more solutions found for max length till 'i'
                    dpCount[i] += dpCount[j];
                }
            }
        }
        
        int maxLength = 0;
        for(int i=0; i<n; i++){
            maxLength = Math.max(maxLength, dpLength[i]);
        }
        
        int count = 0;
        for(int i=0; i<n; i++){
            if(dpLength[i] == maxLength){
                count += dpCount[i];
            }
        }
        
        return count;
    }
}

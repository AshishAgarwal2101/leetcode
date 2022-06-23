/**
 *
 * 416. Partition Equal Subset Sum
 * Medium
 * 
 * 7811
 * 
 * 123
 * 
 * Add to List
 * 
 * Share
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * 
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 
*/



import java.util.*;

class Solution {
    /* -----------------Solution 1: Recursion -------------
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int i=0; i<nums.length; i++){
            totalSum += nums[i];
        }
        
        if(totalSum %2 != 0){
            return false;
        }
        
        return canPartition(nums, totalSum/2, 0);
    }
    
    public boolean canPartition(int[] nums, int remainingSum, int index){
        if(remainingSum == 0){
            return true;
        }
        if(index == nums.length){
            return false;
        }
        
        return canPartition(nums, remainingSum, index+1) || 
            canPartition(nums, remainingSum-nums[index], index+1);
    }
    */
    
    /*----------Solution 2: Dynamic Programming ---------------- 
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int i=0; i<nums.length; i++){
            totalSum += nums[i];
        }
        
        if(totalSum %2 != 0){
            return false;
        }
        
        int remainingSum = totalSum/2;
        boolean[][] dp = new boolean[nums.length][remainingSum+1];
        if(nums[0] <= remainingSum){
            dp[0][nums[0]] = true;
        }
        for(int i=0; i<nums.length; i++){
            dp[i][0] = true;
        }
        for(int i=1; i<nums.length; i++){
            for(int j=1; j<=remainingSum; j++){
                if(j == nums[i]){
                    dp[i][j]= true;
                }
                else {
                    int toReachSum = j; //till the current index, this sum needs to be reached
                    boolean isReacheableTillLastIndex = false;
                    boolean isReacheableTillLastIndexCurrSum = dp[i-1][toReachSum];
                    if(toReachSum - nums[i] > 0){
                        isReacheableTillLastIndex = dp[i-1][toReachSum-nums[i]];
                    }
                    dp[i][j] = isReacheableTillLastIndex || isReacheableTillLastIndexCurrSum;
                }
            }
        }
        
        return dp[nums.length-1][remainingSum];
    }
    */
    
    /* ----------Solution 3: Dynamic Programming - with reduced space ------------
    */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int i=0; i<nums.length; i++){
            totalSum += nums[i];
        }
        
        if(totalSum %2 != 0){
            return false;
        }
        
        int remainingSum = totalSum/2;
        boolean[] dp = new boolean[remainingSum+1]; //simply to store whether it's possible to reach a given sum
        dp[0] = true;
        for(int num:nums){
            for(int i=remainingSum; i>=num; i--){
                dp[i] = dp[i] || dp[i-num];
            }
        }
        
        return dp[remainingSum];
    }
}

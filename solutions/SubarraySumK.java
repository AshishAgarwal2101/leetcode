/**
 *
 * 560. Subarray Sum Equals K:::::::::::::
 * 
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * 
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * 
*/



import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>(); //<Sum, no. of sum for given sum>
        prefixMap.put(0, 1); //for subarray of size 0
        
        int currSum = 0;
        int res = 0;
        for(int num:nums){
            currSum += num;
            if(prefixMap.containsKey(currSum-k)){
                /*
                Lets assume: k=10; currSum=5; currIndex=i; till indices 4 and 7, sum is (currSum-k)
                This means that from (4 to i) and from (7 to i), sum=(currSum-(currSum-k))=k
                */
                res += prefixMap.get(currSum-k);
            }
            prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0)+1);
        }
        
        return res;
    }
}

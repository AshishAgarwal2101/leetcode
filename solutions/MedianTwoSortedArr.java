/**
 *
 * 4. Median of Two Sorted Arrays::::::::::::::::
 * 
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * 
 * Example 2:
 * 
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *  
 * 
 * Constraints:
 * 
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * 
*/



import java.util.*;

class Solution {
    int nums1Mid; //could be negative, when no elements considered for left partition of nums1
    int nums2Mid; //could be negative, when no elements considered for left partition of nums2
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length+nums2.length;
        if(nums2.length < nums1.length){ //make smaller array as nums1 - to perform binary search on it
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        findPartition(nums1, nums2, 0, nums1.length-1, len/2);
        return findMedian(nums1, nums2, nums1Mid, nums2Mid);
    }
    
    public void findPartition(int[] nums1, int[] nums2, int left1, int right1, int leftPartitionTotSize){
        nums1Mid = (int)(Math.floor((left1 + right1)/2.0)); //partition candidate becomes everything from start to nums1Mid
        int nums1PartitionSize = Math.min(nums1Mid+1, nums1.length); //extra check for left1>right1
        nums2Mid = leftPartitionTotSize-nums1PartitionSize-1; 
        if(left1 > right1) return;
        
        boolean isNums1PartitionCorrect = isPartitionCorrect(nums1, nums2, nums1Mid, nums2Mid+1);
        boolean isNums2PartitionCorrect = isPartitionCorrect(nums2, nums1, nums2Mid, nums1Mid+1);
        
        if(isNums1PartitionCorrect && isNums2PartitionCorrect) return;
        if(!isNums1PartitionCorrect){
            //more elements present in nums1 left partition -> move nums1 left partition to left
            findPartition(nums1, nums2, left1, nums1Mid-1, leftPartitionTotSize);
        }
        else{
            //more elements present in nums2 left partition -> move nums1 left partition to right
            findPartition(nums1, nums2, nums1Mid+1, right1, leftPartitionTotSize);
        }
        
    }
    
    public boolean isPartitionCorrect(int[] nums1, int[] nums2, int nums1Index, int nums2Index){
        if(nums2Index == nums2.length) return true;
        return nums1[nums1Index] <= nums2[nums2Index];
    }
    
    public double findMedian(int[] nums1, int[] nums2, int nums1LeftPartitionMaxIndex, int nums2LeftPartitionMaxIndex){
        int nums1RightPartitionMin = (nums1LeftPartitionMaxIndex+1==nums1.length) ? Integer.MAX_VALUE : nums1[nums1LeftPartitionMaxIndex+1];
        int nums2RightPartitionMin = (nums2LeftPartitionMaxIndex+1==nums2.length) ? Integer.MAX_VALUE : nums2[nums2LeftPartitionMaxIndex+1];
        int nums1LeftPartitionMax = (nums1LeftPartitionMaxIndex<0) ? Integer.MIN_VALUE : nums1[nums1LeftPartitionMaxIndex];
        int nums2LeftPartitionMax = (nums2LeftPartitionMaxIndex<0) ? Integer.MIN_VALUE : nums2[nums2LeftPartitionMaxIndex];
        
        int rightMin = Math.min(nums1RightPartitionMin, nums2RightPartitionMin);
        int leftMax = Math.max(nums1LeftPartitionMax, nums2LeftPartitionMax);
    
        if((nums1.length+nums2.length)%2 == 1){ //odd - so single no. median
            return rightMin*1.0;
        }
        
        return (leftMax+rightMin)/2.0;
    }
}

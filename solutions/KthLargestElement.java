/**
 *
 * 215. Kth Largest Element in an Array::::::
 * 
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * 
 * Example 2:
 * 
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *  
 * 
 * Constraints:
 * 
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * 
*/



import java.util.*;

class Solution {
    /* Simplest solution --- brute force
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        reverse(nums);
        return nums[k-1];
    }
    
    public void reverse(int[] nums){
        for(int i=0, j=nums.length-1; i<nums.length/2; i++, j--){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    */
    
    /*
     * Solution 2: Max Heap
     * Can use max heap:
     * Heapify first k elements - root becomes kth largest elemement for first k elements
     * For every other element i, if element less than root element, the make this elem as root and call heapify
    */
    
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }
    
    public int quickSelect(int[] nums, int left, int right, int kSmallestIndex){
        if(left == right){
            System.out.println("equal:: "+left+"---val="+nums[left]);
            return nums[left];
        }
        
        int pivotIndex = partition(nums, left, right);
        if(kSmallestIndex == pivotIndex){
            return nums[pivotIndex];
        }
        if(kSmallestIndex > pivotIndex){
            return quickSelect(nums, pivotIndex+1, right, kSmallestIndex);
        }
        
        return quickSelect(nums, left, pivotIndex-1, kSmallestIndex);
    }
    
    public int partition(int[] nums, int left, int right){
        
        Random random = new Random();
        int pivotIndex = random.nextInt((right+1)-left) + left;
        swap(nums, pivotIndex, right); //now rightmost index becomes the pivot
        
        int pivot = nums[right];
        int i = left;
        for(int j=left; j<=right-1; j++){
            if(nums[j] < pivot){
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right); //swapping last pivot element;
        
        return i;
    }
    
    public void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

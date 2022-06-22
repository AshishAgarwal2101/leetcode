/**
 *
 * 287. Find the Duplicate Number::::::::::::
 * 
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * 
 * There is only one repeated number in nums, return this repeated number.
 * 
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * 
 * Example 2:
 * 
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *  
 * 
 * Constraints:
 * 
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *  
 * 
 * Follow up:
 * 
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 * 
*/



import java.util.*;

class Solution {
    /*
    Fast Slow Pointers::
        This problem is as same as 142. Linked List Cycle II, you can refer to this solution for the explanation of the slow fast pointer approach to solve this problem.

        The key is to understand how to treat the input array as a linked list.

        Take the array [1,3,4,2] as an example, the index of this array is [0, 1, 2, 3], we can map the index to the nums[n].

        0->1
        1->3
        2->4
        3->2
        Start from 0， use value nums[n] as a new index, and so on, until the index exceeds the bounds. This produces a sequence similar to a linked list.

        0->1->3->2->4->null
        If there are a repeated numbers in the array, take the array [1,3,4,2,2] as an example,

        0->1
        1->3
        2->4
        3->2
        4->2
        Similarly, a linked list is like that:

        0->1->3->2->4->2->4->2->…
    */

    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int slow = 0;
        int fast = 0;
        
        //finding the cycle point
        do {
            slow = nums[slow]; 
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        //finding starting point of cycle
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    
    /* --------Solution 2: Modifies array ----------
    public int findDuplicate(int[] nums) {
        //mark the index where no. is found as negative
        //if no. comes again, we'll know it appears more than once
        
        for(int i=0; i<nums.length; i++){
            int num = Math.abs(nums[i]);
            if(nums[num] < 0){
                return num;
            }
            
            //if it comes here, it means nums[num] is +ve
            nums[num] = -1 * nums[num];
        }
        
        return -1;
    }
    */
}

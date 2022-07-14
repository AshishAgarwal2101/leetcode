/**
 *
 * 82. Remove Duplicates from Sorted List II::::::::::::::::::::
 * 
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * 
 * Example 1:
 * 
 * 
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 * 
*/



import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            boolean shouldRemove = false;
            while(curr.next != null && curr.next.val == curr.val){
                shouldRemove = true;
                curr.next = curr.next.next;
            }
            
            if(shouldRemove){
                if(prev == null) head = curr.next;
                else prev.next = curr.next;
            }
            else{
                prev = curr;
            }
            curr = curr.next;
        }
        
        return head;
    }
}

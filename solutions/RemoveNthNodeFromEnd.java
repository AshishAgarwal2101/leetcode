/**
 *
 * 19. Remove Nth Node From End of List::::;
 * 
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Example 2:
 * 
 * Input: head = [1], n = 1
 * Output: []
 * 
 * Example 3:
 * 
 * Input: head = [1,2], n = 1
 * Output: [1]
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *  
 * 
 * Follow up: Could you do this in one pass?
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode nextN = head;
        int currI = 1;
        while(currI < n){
            nextN = nextN.next;
            currI++;
        }
        
        ListNode prev = null;
        ListNode currNode = head;
        while(nextN.next != null){
            prev = currNode;
            currNode = currNode.next;
            nextN = nextN.next;
        }
        
        if(prev == null){
            return currNode.next;
        }
        
        prev.next = currNode.next;
        return head;
    }
}

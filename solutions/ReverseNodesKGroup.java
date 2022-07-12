/**
 *
 * 25. Reverse Nodes in k-Group::::::::::::::
 * 
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * 
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * 
 *  
 * Example 1:
 * 
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * 
 * Example 2:
 * 
 * 
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *  
 * 
 * Follow-up: Can you solve the problem in O(1) extra memory space?
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
    public ListNode reverseKGroup(ListNode head, int k) {
        int size = 0;
        ListNode tmp = head;
        while(tmp != null){
            tmp = tmp.next;
            size++;
        }
        
        int remaining = size;
        ListNode resultHead = null;
        ListNode lastTail = null;
        ListNode nextHead = head;
        while(remaining-k >=0){
            ListNode[] reversed = reverseK(nextHead, k);
            ListNode rotatedHead = reversed[0];
            if(resultHead == null){
                resultHead = rotatedHead; //first rotated head becomes the result head
            }
            if(lastTail != null){
                lastTail.next = rotatedHead;
            }
            lastTail = nextHead;
            nextHead = reversed[1];
            remaining -= k;
        }
        if(lastTail != null){
            lastTail.next = nextHead;
        }
        
        return resultHead;
    }
    
    public ListNode[] reverseK(ListNode head, int k){
        ListNode curr = head;
        ListNode prev = null;
        ListNode next;
        
        for(int i=1; i<=k; i++){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return new ListNode[] {prev, curr}; //returns rotated list's head and next list's head
    }
}

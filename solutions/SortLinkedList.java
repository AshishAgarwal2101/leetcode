/**
 *
 * 148. Sort List::::
 * 
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * 
 * Example 2:
 * 
 * 
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * 
 * Example 3:
 * 
 * Input: head = []
 * Output: []
 *  
 * 
 * Constraints:
 * 
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *  
 * 
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
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
 
 //Merge Sort
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //now slow is in the middle -> divide listing half and sort
        ListNode nextHead = slow.next;
        slow.next = null;
        
        ListNode firstSortedList = sortList(head);
        ListNode secondSortedList = sortList(nextHead);
        
        return mergeList(firstSortedList, secondSortedList);
    }
    
    private ListNode mergeList(ListNode first, ListNode second){
        if(first == null){
            return second;
        }
        if(second == null){
            return first;
        }
        
        ListNode temp1 = first;
        ListNode temp2 = second;
        
        ListNode newHead = new ListNode(); //dummy node
        ListNode newTemp = newHead;
        
        while(temp1 != null && temp2 != null){
            if(temp1.val < temp2.val){
                newTemp.next = temp1;
                temp1 = temp1.next;
            }
            else{
                newTemp.next = temp2;
                temp2 = temp2.next;
            }
            
            newTemp = newTemp.next;
        }
        while(temp1 != null){
            newTemp.next = temp1;
            temp1 = temp1.next;
            newTemp = newTemp.next;
        }
        while(temp2 != null){
            newTemp.next = temp2;
            temp2 = temp2.next;
            newTemp = newTemp.next;
        }
        
        newTemp.next = null;
        ListNode finalHead = newHead.next;
        newHead.next = null;
        
        return finalHead;
    }
    
    public void printList(ListNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}

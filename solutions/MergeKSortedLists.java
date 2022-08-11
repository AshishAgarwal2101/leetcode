/**
 *
 * 23. Merge k Sorted Lists:::::::::::::::;
 * 
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * Example 1:
 * 
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * 
 * Example 2:
 * 
 * Input: lists = []
 * Output: []
 * 
 * Example 3:
 * 
 * Input: lists = [[]]
 * Output: []
 *  
 * 
 * Constraints:
 * 
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
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

/*--------------Solution 1: Min Heap-------------------
public class NodeComparator implements Comparator<ListNode> {
    public int compare(ListNode node1, ListNode node2){
        return node1.val - node2.val;
    }
}
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new NodeComparator());
        for(int i=0; i<lists.length; i++){
            ListNode tmp = lists[i];
            while(tmp != null){
                minHeap.add(tmp);
                tmp = tmp.next;
            }
        }
        
        ListNode head = minHeap.isEmpty() ? null : minHeap.remove();
        ListNode tmp = head;
        while(!minHeap.isEmpty()){
            tmp.next = minHeap.remove();
            tmp = tmp.next;
        }
        if(tmp != null){
            tmp.next = null;
        }
        
        return head;
    }
}
*/

/*-----------------Solution 2: Similar to merge sort------------------*/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        return mergeKListsUtil(lists, 0, lists.length-1);
    }
    
    public ListNode mergeKListsUtil(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];
        int mid = (start + end)/2;
        ListNode l1 = mergeKListsUtil(lists, start, mid);
        ListNode l2 = mergeKListsUtil(lists, mid+1, end);
        
        return mergeTwoSorted(l1, l2);
    }
    
    public ListNode mergeTwoSorted(ListNode node1, ListNode node2){
        ListNode fake = new ListNode();
        ListNode tmp = fake;
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                tmp.next = node1;
                node1 = node1.next;
            }
            else{
                tmp.next = node2;
                node2 = node2.next;
            }
            
            tmp = tmp.next;
        }
        while(node1 != null){
            tmp.next = node1;
            node1 = node1.next;
            tmp = tmp.next;
        }
        while(node2 != null){
            tmp.next = node2;
            node2 = node2.next;
            tmp = tmp.next;
        }
        
        tmp.next = null;
        return fake.next;
    }
    
    public void printlist(ListNode l1){
        System.out.println("New List Print::::::::");
        while(l1 != null){
            System.out.print(l1.val + " -> ");
            l1 = l1.next;
        }
        System.out.println();
    }
}

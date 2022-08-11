/**
 *
 * 378. Kth Smallest Element in a Sorted Matrix::::::::::::::::
 * 
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * You must find a solution with a memory complexity better than O(n2).
 * 
 * Example 1:
 * 
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * 
 * Example 2:
 * 
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *  
 * 
 * Constraints:
 * 
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 *  
 * 
 * Follow up:
 * 
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 * 
*/



import java.util.*;

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>();
        
        if(n == 0) return Integer.MAX_VALUE;
        
        //initialising minHeap with first row elements
        for(int i=0; i<n; i++){
            minHeap.add(new HeapNode(matrix[0][i], 0, i));
        }
        
        //finding 'k' smallest elements, and removing the last found smallest element
        HeapNode ithSmallest = new HeapNode(0, 0, 0); //fake initialization
        for(int i=1; i<=k; i++){
            ithSmallest = minHeap.remove();
            int elem = ithSmallest.val;
            int row = ithSmallest.row;
            int col = ithSmallest.col;
            int nextVal = (row < n-1) ? matrix[row+1][col] : Integer.MAX_VALUE;
            
            minHeap.add(new HeapNode(nextVal, row+1, col)); //infinite val will go to end of minHeap
        }
        
        return ithSmallest.val;
    }
    
    private class HeapNode implements Comparable<HeapNode> {
        int val;
        int row;
        int col;
        
        public HeapNode(int val, int row, int col){
            this.val = val;
            this.row = row;
            this.col = col;
        }
        
        @Override
        public int compareTo(HeapNode nextNode){
            return Integer.compare(this.val, nextNode.val);
        }
    }
}

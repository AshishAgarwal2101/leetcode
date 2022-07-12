/**
 *
 * 84. Largest Rectangle in Histogram:::::::::::::::::::;
 * 
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * 
 * Example 1:
 * 
 * 
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * 
 * Example 2:
 * 
 * 
 * Input: heights = [2,4]
 * Output: 4
 *  
 * 
 * Constraints:
 * 
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 * 
*/



import java.util.*;

class Node{
    int val;
    int index;
    Node(int index, int val){
        this.index = index;
        this.val = val;
    }
}

class Solution {
    /*------------------Time complexity: O(n)---------------------*/
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Node> stack = new Stack<>();
        int[] left = new int[heights.length]; //index to the left where val smaller than curr val
        int[] right = new int[heights.length]; //index to the right where val smaller than curr val
        
        stack.push(new Node(-1, Integer.MIN_VALUE));
        for(int i=0; i<left.length; i++){
            while(!stack.isEmpty()){
                Node node = stack.pop(); //stack values larger than curr value not needed
                if(node.val < heights[i]){
                    left[i] = node.index;
                    stack.push(node);
                    break;
                }
            }
            stack.push(new Node(i, heights[i]));
        }
        
        stack = new Stack<>();
        stack.push(new Node(heights.length, Integer.MIN_VALUE));
        for(int i=right.length-1; i>=0; i--){
            while(!stack.isEmpty()){
                Node node = stack.pop(); //stack values larger than curr value not needed
                if(node.val < heights[i]){
                    right[i] = node.index;
                    stack.push(node);
                    break;
                }
            }
            stack.push(new Node(i, heights[i]));
        }
        
        for(int i=0; i<heights.length; i++){
            int length = (right[i]-1) - (left[i]+1) + 1;
            int height = heights[i];
            int area = length*height;
            if(area > max) max = area;
        }
        
        return max;
    }
}

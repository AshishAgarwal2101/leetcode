/**
 *
 * 295. Find Median from Data Stream:::::::::::::::::
 * 
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 * 
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * 
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *  
 * 
 * Example 1:
 * 
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * 
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *  
 * 
 * Constraints:
 * 
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 *  
 * 
 * Follow up:
 * 
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * 
*/



import java.util.*;

class MedianFinder {
    PriorityQueue<Integer> leftMaxHeap; //left of median (inclusive) - this would include extra elem if total count=odd
    PriorityQueue<Integer> rightMinHeap; //rigth of median (inclusive or exclusive)
    
    public MedianFinder() {
        leftMaxHeap = new PriorityQueue(Collections.reverseOrder());
        rightMinHeap = new PriorityQueue();
    }
    
    public void addNum(int num) {
        /* ------------Solution 1----------------
        if(leftMaxHeap.size() == rightMinHeap.size()){
            if(rightMinHeap.size() != 0 && rightMinHeap.peek() < num){
                //interchange elements from rightMinHeap
                rightMinHeap.add(num);
                num = rightMinHeap.remove();
            }
            leftMaxHeap.add(num);
        }
        else{
            if(leftMaxHeap.size() != 0 && leftMaxHeap.peek() > num){
                //interchange elements from leftMaxHeap
                leftMaxHeap.add(num);
                num = leftMaxHeap.remove();
            }
            rightMinHeap.add(num);
        }*/
        
        /* ------------------Solution 2: Simpler---------------*/
        if(leftMaxHeap.size() == 0 || num <= leftMaxHeap.peek()) leftMaxHeap.add(num);
        else rightMinHeap.add(num);
        rebalance();
    }
    
    public double findMedian() {
        if(leftMaxHeap.size() == rightMinHeap.size()){ //total count = even
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        }
        
        return leftMaxHeap.peek() * 1.0;
    }
    
    private void rebalance(){
        if(leftMaxHeap.size() - rightMinHeap.size() > 1){
            rightMinHeap.add(leftMaxHeap.remove());
        }
        else if(rightMinHeap.size() - leftMaxHeap.size() > 0){
            leftMaxHeap.add(rightMinHeap.remove());
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

/**
 *
 * 347. Top K Frequent Elements::::::::::::
 * 
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Example 2:
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 *  
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *  
 * 
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * 
*/



import java.util.*;

class Solution {
    /* ----------Solution 1: Max Heap----------
    Time Coplexity: O(n log n)
    */
    class Node implements Comparable<Node>{
        int num;
        int count;
        
        Node(){}
        
        Node(int num, int count){
            this.num = num;
            this.count = count;
        }
        
        @Override
        public int compareTo(Node n2){
            return (n2.count - this.count);
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        //lower the value of integer, more is the priority - reversed logic in compareTo()
        PriorityQueue<Node> maxHeap = new PriorityQueue<>();
        
        Map<Integer, Integer> numCount = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int count = numCount.getOrDefault(nums[i], 0);
            numCount.put(nums[i], count+1);
        }
        
        for(int num:numCount.keySet()){
            int count = numCount.get(num);
            Node node = new Node(num, count);
            maxHeap.add(node);
        }
        
        //extract first 'k' elements from max heap
        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = maxHeap.poll().num;
        }
        
        return res;
    }
    
    /* ------------Solution 2-------------
    Time complexity: O(n)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int count = numCount.getOrDefault(nums[i], 0);
            numCount.put(nums[i], count+1);
        }
        
        //below array contains list of numbers for a given frequency/count
        List<Integer>[] frequencyNums = new ArrayList[nums.length+1];
        
        for(int num:numCount.keySet()){
            int count = numCount.get(num);
            if(frequencyNums[count] == null){
               frequencyNums[count] = new ArrayList<>();
            }
            frequencyNums[count].add(num);
        }
        
        int[] res = new int[k];
        int index = 0;
        for(int i=frequencyNums.length-1; i>0; i--){
            if(frequencyNums[i] != null){
                List<Integer> currFreqNums = frequencyNums[i];
                //below - iterating through the array list
                for(int j=0; j<currFreqNums.size(); j++){
                    int num = currFreqNums.get(j);
                    res[index++] = num;
                    if(index == k){
                        return res;
                    }
                }
            }
        }
        
        return res;
    }
    */
}

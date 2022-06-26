/**
 *
 * 77. Combinations:::::::::::;
 * 
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * 
 * You may return the answer in any order.
 * 
 * Example 1:
 * 
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 
 * Example 2:
 * 
 * Input: n = 1, k = 1
 * Output: [[1]]
 *  
 * 
 * Constraints:
 * 
 * 1 <= n <= 20
 * 1 <= k <= n
 * 
*/



import java.util.*;

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> curr = new ArrayList<>();
        combine(1, n, k, curr);
        
        return res;
    }
    
    public void combine(int index, int maxIndex, int remainingCount, List<Integer> curr){
        if(remainingCount == 0){
            res.add(new ArrayList<>(curr));
            return;
        }
        if((maxIndex-index+1) < remainingCount){
            return;
        }
        
        curr.add(index);
        combine(index+1, maxIndex, remainingCount-1, curr);
        curr.remove(curr.size()-1);
        combine(index+1, maxIndex, remainingCount, curr);
    }
}

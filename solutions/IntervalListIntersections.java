/**
 *
 * 986. Interval List Intersections::::::::::::
 * 
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 * 
 * Return the intersection of these two interval lists.
 * 
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * 
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 * 
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *  
 * 
 * Constraints:
 * 
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 109
 * endi < starti+1
 * 0 <= startj < endj <= 109
 * endj < startj+1
 * 
*/



import java.util.*;

class Solution {
    /* ---------------Solution 1---------------
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int firstLen = firstList.length;
        int secondLen = secondList.length;
        int i=0;
        int j=0;
        while(i<firstLen && j<secondLen){
            if(firstList[i][0] <= secondList[j][0]){ //firstList has lower start
                if(firstList[i][1] >= secondList[j][0]){ //second has intersection to first
                    res.add(getIntersection(firstList[i][0], firstList[i][1], secondList[j][0], secondList[j][1]));
                }
            }
            else{ //secondList has lower start
                if(secondList[j][1] >= firstList[i][0]){ //first has intersection to second
                    res.add(getIntersection(secondList[j][0], secondList[j][1], firstList[i][0], firstList[i][1]));
                }
            }
            
            if(firstList[i][1] <= secondList[j][1] && secondList[j][1] <= firstList[i][1]){
                i++;
                j++;
            }
            else if (firstList[i][1] <= secondList[j][1]) i++;
            else j++;
        }
        
        return mergeCommonIntersections(res);
    }
    
    public int[] getIntersection(int startI, int endI, int startJ, int endJ){
        int resStart  = startJ;
        int resEnd = Math.min(endI, endJ);
        return new int[]{resStart, resEnd};
    }
    
    public int[][]  mergeCommonIntersections(List<int[]> intervals){
        List<int[]> res = new ArrayList<>();
        for(int i=0; i<intervals.size(); i++){
            if(i != intervals.size()-1 && intervals.get(i)[1] == intervals.get(i+1)[0]){
                intervals.get(i)[1] = intervals.get(i+1)[1];
                res.add(intervals.get(i));
                i++; //already processed next interval
            }
            else{
                res.add(intervals.get(i));
            }
        }
        
        return res.toArray(new int[res.size()][2]);
    }
    */
    
    /* ----------------------Solution 2----------------------*/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<firstList.length && j<secondList.length){
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if(start <= end){ //intersection exist
                res.add(new int[]{start, end});
            }
            
            if(firstList[i][1] < secondList[j][1]) i++;
            else j++;
        }
        
        return res.toArray(new int[res.size()][2]);
    }
}

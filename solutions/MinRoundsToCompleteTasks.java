/**
 *
 * 2244. Minimum Rounds to Complete All Tasks:::::::::::::::
 * 
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 * 
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 * 
 * Example 1:
 * 
 * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
 * Output: 4
 * Explanation: To complete all the tasks, a possible plan is:
 * - In the first round, you complete 3 tasks of difficulty level 2. 
 * - In the second round, you complete 2 tasks of difficulty level 3. 
 * - In the third round, you complete 3 tasks of difficulty level 4. 
 * - In the fourth round, you complete 2 tasks of difficulty level 4.  
 * It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
 * 
 * Example 2:
 * 
 * Input: tasks = [2,3,3]
 * Output: -1
 * Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
 *  
 * 
 * Constraints:
 * 
 * 1 <= tasks.length <= 105
 * 1 <= tasks[i] <= 109
 * 
*/



import java.util.*;

class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> diffMap = new HashMap<>();
        for(int i=0; i<tasks.length; i++){
            int count = diffMap.getOrDefault(tasks[i], 0);
            diffMap.put(tasks[i], count+1);
        }

        int res = 0;
        for(int diffLevel:diffMap.keySet()){
            int diffCount = diffMap.get(diffLevel);
            int steps = getMinSteps(diffCount);
            if(steps == -1) return -1;
            res += steps;
        }

        return res;
    }


    /* ------------Method 1 to get "min steps" ONLY----------
    public int getMinSteps(int diffCount){
        //we need to minimize (2x+3y)=diffCount such that x>=0 and y>=0, where x,y=(0,1,2,3,4..)
        //in other words, we need to maximize 'y'
        //max value of x=(diffCount/2) - substituting y=0
        //max value of y=(diffCount/3) - substituting x=0
        int y = diffCount/3;

        int currY = y;
        while(currY>=0){
            int remainder = diffCount - (currY*3);
            if(remainder % 2 == 0){
                return (currY + remainder/2);
            }
            currY--;
        }

        return -1;
    }
    */

    /* ------------Method 2 to get "min steps" ONLY----------
    2 tasks will be considered either never or only once (line 2 of function) or twice (line 3 of function)
     */
    public int getMinSteps(int diffCount) {
        if (diffCount % 3 == 0) return diffCount / 3;
        if (diffCount % 3 == 2) return (diffCount - 2) / 3 + 1;
        if(diffCount>=4 && (diffCount - 4) % 3 == 0) return (diffCount - 4) / 3 + 2;
        return -1;
    }
}

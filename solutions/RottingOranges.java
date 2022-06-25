/**
 *
 * 994. Rotting Oranges::::::::::::
 * 
 * You are given an m x n grid where each cell can have one of three values:
 * 
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 * 
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * 
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *  
 * 
 * Constraints:
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 * 
*/



import java.util.*;

class Solution {
    class Node {
        int x;
        int y;
        Node(){}
        Node(int x, int y){ this.x = x; this.y = y; }
    }
    
    int directions[][] = new int[][]{ {0,-1}, {0,1}, {-1,0}, {1,0} };
    int rowCount;
    int colCount;
    
    public int orangesRotting(int[][] grid) {
        //first find min distance from a fresh orange to a rotten orange (throgh fresh oranges)
        //then, max of these distances is the result
        
        rowCount = grid.length;
        colCount = grid[0].length;
        int[][] minDistance = new int[rowCount][colCount];
        
        Queue<Node> potentialUpdaters = new LinkedList<>();
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<colCount; j++){
                if(grid[i][j] == 2){ //rotten
                    minDistance[i][j] = 0;
                    potentialUpdaters.add(new Node(i,j));
                }
                else {
                    minDistance[i][j] = 1000; //assuming 1000 to be infinite
                }
            }
        }
        
        while(!potentialUpdaters.isEmpty()){
            Node node = potentialUpdaters.remove();
            updateNeighbors(minDistance, grid, potentialUpdaters, node.x, node.y);
        }
        
        //max of all mins is the result
        int max = 0; //default case when no fresh oranges
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<colCount; j++){
                if(grid[i][j] == 1 && minDistance[i][j] > max){
                    max = minDistance[i][j];
                }
            }
        }
        
        if(max >= 1000){ //fresh oranges not connected to rotten
            return -1;
        }
        return max;
    }
    
    public void updateNeighbors(int[][] minDistance, int[][] grid, Queue<Node> potentialUpdaters, int x, int y){
        for(int i=0; i<directions.length; i++){
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];
            if(nx>=0 && nx<rowCount && ny>=0 && ny<colCount && grid[nx][ny]!=0){
                if(minDistance[x][y]+1 < minDistance[nx][ny]){
                    minDistance[nx][ny] = minDistance[x][y] + 1;
                    potentialUpdaters.add(new Node(nx,ny));
                }
            }
        }
    }
}

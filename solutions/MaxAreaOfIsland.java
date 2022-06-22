/**
 *
 * 695. Max Area of Island::::::::;
 * 
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * 
 * The area of an island is the number of cells with a value 1 in the island.
 * 
 * Return the maximum area of an island in grid. If there is no island, return 0.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * 
 * Example 2:
 * 
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *  
 * 
 * Constraints:
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 * 
*/



import java.util.*;

class Solution {
    int rowCount;
    int colCount;
    public int maxAreaOfIsland(int[][] grid) {
        rowCount = grid.length;
        colCount = grid[0].length;
        int maxArea = 0;
        boolean[][] visited = new boolean[rowCount][colCount];
        
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<colCount; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    int area = maxArea(grid, visited, i, j);
                    if(area > maxArea){
                        maxArea = area;
                    }
                }
            }
        }
        
        return maxArea;
    }
    
    public int maxArea(int[][] grid, boolean[][] visited, int x, int y){
        if(x<0 || x>=rowCount || y<0 || y>=colCount){
            return 0;
        }
        if(visited[x][y] || grid[x][y] == 0){
            return 0;
        }
        visited[x][y] = true;
        
        int left = maxArea(grid, visited, x-1, y);
        int right = maxArea(grid, visited, x+1, y);
        int top = maxArea(grid, visited, x, y-1);
        int bottom = maxArea(grid, visited, x, y+1);
        
        return (1+left+right+top+bottom);
    }
}

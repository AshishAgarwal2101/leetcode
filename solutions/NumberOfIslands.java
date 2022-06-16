/**
 *
 * 200. Number of Islands::::::::
 * 
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * 
 * Example 2:
 * 
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *  
 * 
 * Constraints:
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 * 
*/



import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0;
        boolean[][] visited = new boolean[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    numIslands++;
                    markVisited(grid, visited, i, j, rows, cols);
                }
                else{
                    visited[i][j] = true;
                }
            }
        }
        
        return numIslands;
    }
    
    public void markVisited(char[][] grid, boolean[][] visited, int i, int j, int rows, int cols){
        if(i > rows-1 || j > cols-1 || i<0 || j<0){
            return;
        }
        if(grid[i][j] == '0' || visited[i][j]){
            visited[i][j] = true;
            return;
        }
        visited[i][j] = true;
        markVisited(grid, visited, i+1, j, rows, cols);
        markVisited(grid, visited, i, j+1, rows, cols);
        markVisited(grid, visited, i-1, j, rows, cols);
        markVisited(grid, visited, i, j-1, rows, cols);
    }
}

/**
 *
 * 1091. Shortest Path in Binary Matrix:::::::::::::::
 * 
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * 
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * 
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * 
 *  
 * Example 1:
 * 
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * 
 * Example 3:
 * 
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *  
 * 
 * Constraints:
 * 
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 * 
*/



import java.util.*;

class Solution {
    /* ---------------Solution 1: DFS: Inefficient as scans the whole board--------------
    public int shortestPathBinaryMatrix(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = pathUtil(grid, visited, 0, 0);
        if(res == Integer.MAX_VALUE) return -1;
        return res;
    }
    
    public int pathUtil(int[][] grid, boolean[][] visited, int x, int y){
        if(x<0 || y<0 || x>=grid.length || y>=grid[0].length) return Integer.MAX_VALUE;
        if(grid[x][y] == 1 || visited[x][y]) return Integer.MAX_VALUE;
        if(x == grid.length-1 && y == grid[0].length-1) return 1;
        
        visited[x][y] = true;
        int left = pathUtil(grid, visited, x, y-1);
        int right = pathUtil(grid, visited, x, y+1);
        int top = pathUtil(grid, visited, x-1, y);
        int bottom = pathUtil(grid, visited, x+1, y);
        int topLeft = pathUtil(grid, visited, x-1, y-1);
        int topRight = pathUtil(grid, visited, x-1, y+1);
        int bottomLeft = pathUtil(grid, visited, x+1, y-1);
        int bottomRight = pathUtil(grid, visited, x+1, y+1);
        visited[x][y] = false;
        
        int minPath = min(left, right, top, bottom, topLeft, topRight, bottomLeft, bottomRight);
        if(minPath == Integer.MAX_VALUE) return minPath;
        return 1 + minPath;
    }
    
    public int min(int a, int b, int c, int d, int e, int f, int g, int h){
        int res = Math.min(a, b);
        res = Math.min(res, c);
        res = Math.min(res, d);
        res = Math.min(res, e);
        res = Math.min(res, f);
        res = Math.min(res, g);
        res = Math.min(res, h);
        
        return res;
    }
    */
    
    /* -----------------Solution 2: BFS ------------------------*/
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1) return -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int directions[][] = new int[][]{
            new int[]{0,-1}, new int[]{0,1},
            new int[]{-1,0}, new int[]{1,0},
            new int[]{-1,-1}, new int[]{-1,1},
            new int[]{1,-1}, new int[]{1,1}
        };
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1}); //{x,y,distance}
        
        while(!queue.isEmpty()){
            int[] curr = queue.remove();
            int x = curr[0];
            int y = curr[1];
            int distance = curr[2];
            if(x == grid.length-1 && y == grid[0].length-1) return distance;
            
            for(int i=0; i<directions.length; i++){
                int newX = x + directions[i][0];
                int newY = y + directions[i][1];
                if(newX<0 || newY<0 || newX>=grid.length || newY>= grid[0].length) continue;
                if(visited[newX][newY] || grid[newX][newY] == 1) continue;
                
                visited[newX][newY] = true; //no need to reset visited as this node would be visited at a lower level
                queue.add(new int[]{newX, newY, distance+1});
            }
        }
        
        return -1;
    }
}

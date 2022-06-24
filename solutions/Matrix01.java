/**
 *
 * 542. 01 Matrix::::::::::::
 * 
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * 
 * The distance between two adjacent cells is 1.
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * Example 2:
 * 
 * 
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *  
 * 
 * Constraints:
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 * 
*/



import java.util.*;

class Solution {
    int rowCount;
    int colCount;
    
    /* ---------------Solution 1: Recursion: TLE-------------------
    public int[][] updateMatrix(int[][] mat) {
        rowCount = mat.length;
        colCount = mat[0].length;
        int[][] nearestZeroes = new int[rowCount][colCount];
        boolean[][] visited = new boolean[rowCount][colCount];
        initMatrix(nearestZeroes, -1);
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<colCount; j++){
                if(mat[i][j] == 0){
                    nearestZeroes[i][j] = 0;
                }
                if(nearestZeroes[i][j] == -1){
                    nearestZeroes[i][j] = getNearestZero(mat, nearestZeroes, visited, i, j);
                }
            }
        }
        
        return nearestZeroes;
    }
    
    public int getNearestZero(int[][] mat, int[][] nearestZeroes, boolean[][] visited, int x, int y){
        if(x<0 || x>=rowCount || y<0 || y>=colCount){
            return Integer.MAX_VALUE;
        }
        if(mat[x][y] == 0){
            return 0;
        }
        if(nearestZeroes[x][y] != -1){
            return nearestZeroes[x][y];
        }
        if(visited[x][y]){
            return Integer.MAX_VALUE;
        }
        
        visited[x][y] = true;
        int left = getNearestZero(mat, nearestZeroes, visited, x, y-1);
        int right = getNearestZero(mat, nearestZeroes, visited, x, y+1);
        int top = getNearestZero(mat, nearestZeroes, visited, x-1, y);
        int bottom = getNearestZero(mat, nearestZeroes, visited, x+1, y);
        visited[x][y] = false;
        
        int min = getMin(left, right, top, bottom);
        if(min == 0){ //0 is available next to it, no further calculation required for this cell
            nearestZeroes[x][y] = 1;
        }
        if(min == Integer.MAX_VALUE){
            return min;
        }
        return 1+min;
    }
    
    public void initMatrix(int[][] matrix, int val){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = val;
            }
        }
    }
    
    public int getMin(int a, int b, int c, int d){
        return Math.min(a, Math.min(b, Math.min(c, d)));
    }
    */
    
    
    /* -------------Solution 2 ------------------
    public int[][] updateMatrix(int[][] mat) {
        rowCount = mat.length;
        colCount = mat[0].length;
        int[][] nearestZeroes = new int[rowCount][colCount];
        initMatrix(nearestZeroes, Integer.MAX_VALUE);
        fillNearestZeroes(mat, nearestZeroes);
        
        return nearestZeroes;
    }
    
    public void fillNearestZeroes(int[][] mat, int[][] nearestZeroes){
        int remainingFills = Math.max(rowCount, colCount)-1; //this many passes at required at max to fill all cells
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<colCount; j++){
                if(mat[i][j] == 0){
                    nearestZeroes[i][j] = 0;
                }
            }
        }
        
        while(remainingFills > 0){
            for(int i=0; i<rowCount; i++){
                for(int j=0; j<colCount; j++){
                    if(nearestZeroes[i][j] != Integer.MAX_VALUE){
                        updateNeighbors(nearestZeroes, i, j);
                    }
                }
            }
            remainingFills--;
        }
    }
    
    public void updateNeighbors(int[][] nearestZeroes, int x, int y){
        if(y > 0){ //left
            nearestZeroes[x][y-1] = Math.min(nearestZeroes[x][y]+1, nearestZeroes[x][y-1]);
        }
        if(y < colCount-1){ //right
            nearestZeroes[x][y+1] = Math.min(nearestZeroes[x][y]+1, nearestZeroes[x][y+1]);
        }
        if(x > 0){ //top
            nearestZeroes[x-1][y] = Math.min(nearestZeroes[x][y]+1, nearestZeroes[x-1][y]);
        }
        if(x < rowCount-1){ //bottom
            nearestZeroes[x+1][y] = Math.min(nearestZeroes[x][y]+1, nearestZeroes[x+1][y]);
        }
    }
    
    public void initMatrix(int[][] matrix, int val){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                matrix[i][j] = val;
            }
        }
    }
    */
    
    /*---------------Solution 3: Optimization over solution 2-------------
    class Node {
        int x;
        int y;
        
        Node(){}
        
        Node(int x, int y){
            this.x = x; 
            this.y = y;
        }
    }
    
    int[][] directions = new int[][]{{0,-1}, {0,1}, {-1,0}, {1,0}};
    Queue<Node> potentialUpdaters = new LinkedList<>();
    
    public int[][] updateMatrix(int[][] mat) {
        rowCount = mat.length;
        colCount = mat[0].length;
        int[][] nearestZeroes = new int[rowCount][colCount];
        initMatrix(nearestZeroes, mat, Integer.MAX_VALUE);
        fillNearestZeroes(mat, nearestZeroes);
        
        return nearestZeroes;
    }
    
    public void fillNearestZeroes(int[][] mat, int[][] nearestZeroes){
        //System.out.println("size===="+potentialUpdaters.size());
        while(!potentialUpdaters.isEmpty()){
            Node node = potentialUpdaters.remove();
            //System.out.println("\nUpdating neighbors========== x="+node.x+"---y="+node.y);
            updateNeighbors(nearestZeroes, node.x, node.y);
        }
    }
    
    public void updateNeighbors(int[][] nearestZeroes, int x, int y){
        for(int i=0; i<directions.length; i++){
            int nx = directions[i][0] + x;
            int ny = directions[i][1] + y;
            //System.out.println("nx="+nx+"---ny="+ny);
            if(nx>=0 && nx<rowCount && ny>=0 && ny<colCount){
                if((nearestZeroes[x][y]+1) < nearestZeroes[nx][ny]){
                    nearestZeroes[nx][ny] = nearestZeroes[x][y]+1;
                    potentialUpdaters.add(new Node(nx, ny)); //updated cells become potential updaters
                }
            }
        }
    }
    
    public void initMatrix(int[][] matrix, int[][] zeroMatrix, int val){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(zeroMatrix[i][j] == 0){
                    matrix[i][j] = 0;
                    potentialUpdaters.add(new Node(i, j));
                }
                else {
                    matrix[i][j] = val;
                }
            }
        }
    }
    */
    
    /* ---------------Solution 4: Most optimized and simple-------------
    */
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        
        int max = 100000;
        
        // go top to bottom and left to right
        for (int i = 0; i< rows; i++) {
            for (int j = 0; j<cols; j++) {
                if (mat[i][j] != 0) {
                    int top = (i-1) >= 0? mat[i-1][j] : max;
                    int left = (j-1) >=0? mat[i][j-1] : max;
                
                    mat[i][j] = Math.min(top, left) + 1;
                }
                
            }
        }
        
        // go bottom to top and right to left
        for (int i = rows -1; i>=0; i--) {
            for (int j = cols -1; j>=0; j--) {
                if (mat[i][j] != 0) {
                    int bottom = (i+1) < rows ? mat[i+1][j] : max;
                    int right = (j+1) < cols  ? mat[i][j+1] : max;
                    mat[i][j] = Math.min(Math.min(bottom, right) + 1, mat[i][j]);
                }
                
            }
        }
        
        return mat;
    }
}

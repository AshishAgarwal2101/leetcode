/**
 *
 * 79. Word Search::::::::::::::::::
 * 
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * 
 * 
 * Example 1:
 * 
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 * 
 * 
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 * 
 * 
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *  
 * 
 * Constraints:
 * 
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *  
 * 
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 * 
*/



import java.util.*;

class Solution {
    public boolean exist(char[][] board, String word) {
        char[] letters = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(isAvailable(board, letters, visited, i, j, 0)) return true;
            }
        }
        
        return false;
    }
    
    public boolean isAvailable(char[][] board, char[] letters, boolean[][] visited, int x, int y, int wordIndex){
        if(wordIndex == letters.length) return true;
        if(x<0 || x>=board.length || y<0 || y>=board[0].length) return false;
        if(visited[x][y]) return false;
        if(board[x][y] != letters[wordIndex]) return false;
        
        visited[x][y] = true;
        boolean res = isAvailable(board, letters, visited, x, y-1, wordIndex+1)
            || isAvailable(board, letters, visited, x, y+1, wordIndex+1)
            || isAvailable(board, letters, visited, x-1, y, wordIndex+1)
            || isAvailable(board, letters, visited, x+1, y, wordIndex+1);
        //System.out.println("x="+x+"---y="+y+"---res="+res);
        visited[x][y] = false;
        
        return res;
    }
}

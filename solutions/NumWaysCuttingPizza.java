/**
 *
 * 1444. Number of Ways of Cutting a Pizza:::::::::::::
 * 
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts. 
 * 
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 * 
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3 
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 * 
 * Example 2:
 * 
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 * 
 * Example 3:
 * 
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 *  
 * 
 * Constraints:
 * 
 * 1 <= rows, cols <= 50
 * rows == pizza.length
 * cols == pizza[i].length
 * 1 <= k <= 10
 * pizza consists of characters 'A' and '.' only.
 * 
 * 
*/



import java.util.*;

class Solution {
    public int ways(String[] pizza, int k) {
        int rows = pizza.length;
        int cols = pizza[0].length();
        int[][] numApples = new int[rows+1][cols+1]; //numApples[i][j] = num of apples from 'i' to (rows-1) and from 'j' to (cols-1)
        Integer[][][] mem = new Integer[k][rows][cols];
        
        for(int i=rows-1; i>=0; i--){
            for(int j=cols-1; j>=0; j--){
                numApples[i][j] = numApples[i+1][j] + numApples[i][j+1] - numApples[i+1][j+1] 
                    + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
        
        return recurse(numApples, mem, 0, 0, k-1, rows, cols);
    }
    
    public int recurse(int[][] numApples, Integer[][][] mem, int i, int j, int k, int rows, int cols){
        if(numApples[i][j] == 0){ //remaining piece has no apples
            return 0;
        }
        if(k == 0){ //completed all cuts
            return 1;
        }
        if(mem[k][i][j] != null){ //already found before
            return mem[k][i][j];
        }
        
        mem[k][i][j] = 0;
        for(int newR=i+1; newR<rows; newR++){
            if(numApples[i][j] - numApples[newR][j] > 0){ //only cut if upper part has at least an apple
                int next = recurse(numApples, mem, newR, j, k-1, rows, cols);
                mem[k][i][j] = (mem[k][i][j] + next) % 1000000007;
            }
        }
        for(int newC=j+1; newC<cols; newC++){
            if(numApples[i][j] - numApples[i][newC] > 0){ //only cut if left part has at least an apple
                int next = recurse(numApples, mem, i, newC, k-1, rows, cols);
                mem[k][i][j] = (mem[k][i][j] + next) % 1000000007;
            }
        }
        
        return mem[k][i][j];
    }
}

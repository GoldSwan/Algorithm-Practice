package algorithm.leetcode;

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];

        dp[0][1] = 1; // 시작 위치 초기화
        
        for(int y=1; y<=m; y++)
          for(int x=1; x<=n; x++)
            dp[y][x] = dp[y-1][x] + dp[y][x-1];

        return dp[m][n];
    }
}

public class UniquePaths {
    public static void main(String[] args) throws Exception {
        int m = 3;
        int n = 7;
        var solution = new Solution();
        int answer = solution.uniquePaths(m, n);
        System.out.println(answer);
    }
}

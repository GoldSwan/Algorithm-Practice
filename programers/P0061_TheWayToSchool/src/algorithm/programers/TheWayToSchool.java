package algorithm.programers;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        int[][] map = new int[n+1][m+1];
        final int MOD = 1000000007;
        final int EMPTY_LOAD = -1;

        for(int[] p : puddles)
          map[p[1]][p[0]] = EMPTY_LOAD;

        dp[0][1] = 1; // 시작 위치 초기화
        //dp[1][0] = 1; // 또는 이렇게 초기화 해도 됨

        for(int y=1; y<=n; y++)
          for(int x=1; x<=m; x++)
            dp[y][x] = (map[y][x] == EMPTY_LOAD) ? 0 : (dp[y-1][x] + dp[y][x-1]) % MOD;

        return dp[n][m];
    }
}

public class TheWayToSchool  {
    public static void main(String[] args) throws Exception {
      var solution = new Solution();
      int m = 4;
      int n = 3;
      int[][] puddles = {{2,2}};
      int answer = solution.solution(m, n, puddles);
      System.out.println(answer);
    }

}

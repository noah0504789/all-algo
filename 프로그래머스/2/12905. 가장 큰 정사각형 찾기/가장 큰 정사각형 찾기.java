class Solution
{
    public int solution(int[][] board)
    {
        int n = board.length;
        int m = board[0].length;
        int[][] dp = new int[n+1][m+1];
        
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i-1][j-1] == 1) {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }                
            }
        }
        
        return max * max;
    }
}
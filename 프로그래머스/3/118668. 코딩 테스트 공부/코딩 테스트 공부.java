import java.util.*;

class Solution {
    private int n, m, p_cnt, INF = Integer.MAX_VALUE;
    private int[] alp_req, cop_req;
    private int[] alp_rwd, cop_rwd;
    private int[] cost;
    private int[][] dp; // 알고력,코딩력
    
    public int solution(int alp, int cop, int[][] problems) {
        p_cnt = problems.length;
        
        alp_req = new int[p_cnt];
        cop_req = new int[p_cnt];
        alp_rwd = new int[p_cnt];
        cop_rwd = new int[p_cnt];
        cost = new int[p_cnt];
        
        for (int i = 0; i < p_cnt; i++) {
            int[] problem = problems[i];
            alp_req[i] = problem[0];
            cop_req[i] = problem[1];
            alp_rwd[i] = problem[2];
            cop_rwd[i] = problem[3];
            cost[i] = problem[4];
            
            n = Math.max(n, alp_req[i]);
            m = Math.max(m, cop_req[i]);
        }
        
        alp = Math.min(alp, n);
        cop = Math.min(cop, m);
        
        dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);        
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= n; i++) {            
            for (int j = cop; j <= m; j++) {
                if (dp[i][j] == INF) continue;
                
                if (i+1<=n) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                if (j+1<=m) dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                                
                for (int k = 0; k < p_cnt; k++) {
                    int a_r = alp_req[k], c_r = cop_req[k], cost_ = cost[k];
                    int a_rwd = alp_rwd[k], c_rwd = cop_rwd[k];
                    
                    if (i >= a_r && j >= c_r) {
                        int ni = Math.min(n, i+a_rwd);
                        int nj = Math.min(m, j+c_rwd);
                        
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j]+cost_);
                    }
                }
            }            
        }
        
        return dp[n][m];
    }
}
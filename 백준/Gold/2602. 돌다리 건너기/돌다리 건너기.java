import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m;
    private static char[] du;
    private static char[][] road; // (0-악,1-천)
    private static int[][][] dp; // 두루마리 인덱스, 다리열, 돌다리(0-악,1-천)
    
    public static void main(String... args) throws IOException {
        du = br.readLine().toCharArray();
        
        road = new char[2][];
        road[0] = br.readLine().toCharArray();
        road[1] = br.readLine().toCharArray();
        
        n = du.length;
        m = road[0].length;
        
        dp = new int[n][m][2];
        
        for (int i = 0; i < m; i++) {
            if (road[0][i] == du[0]) dp[0][i][0] = 1;
            if (road[1][i] == du[0]) dp[0][i][1] = 1;
        }
        
        for (int t = 1; t < n; t++) {
            for (int r = 0; r < 2; r++) {
                int or = 1-r;
                for (int i = 0; i < m; i++) {
                    if (road[r][i] != du[t]) continue;
                    int sum = 0;
                    for (int j= 0; j < i; j++) sum += dp[t-1][j][or];
                    dp[t][i][r] = sum;
                }
            }
        }
        
        long ans =0;
        for (int i = 0; i < m; i++) {
            ans += dp[n-1][i][0];
            ans += dp[n-1][i][1];
        }
        
        System.out.print(ans);
    }
    
}
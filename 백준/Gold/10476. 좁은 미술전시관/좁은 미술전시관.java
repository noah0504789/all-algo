import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static long sum, INF = Long.MAX_VALUE;
    private static int[][] arr;
    private static long[][][] dp; // row, 닫은갯수, row 상태(0-모두안닫음, 1-왼쪽닫음, 2-오른쪽닫음)
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        arr = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            arr[i][0] = readInt();
            arr[i][1] = readInt();
            sum += (arr[i][0] + arr[i][1]);
        }        
        
        dp = new long[n+1][k+1][3];
        for (int i = 0; i <= n; i++) {
            for (int t = 0; t <= k; t++) Arrays.fill(dp[i][t], INF);
        }
        dp[0][0][0] = 0;
        
        for (int r = 1; r <= n; r++) {
            for (int t = 0; t <= k; t++) {
                if (dp[r-1][t][0] < INF) dp[r][t][0] = Math.min(dp[r][t][0], dp[r-1][t][0]);
                if (dp[r-1][t][1] < INF) dp[r][t][0] = Math.min(dp[r][t][0], dp[r-1][t][1]);
                if (dp[r-1][t][2] < INF) dp[r][t][0] = Math.min(dp[r][t][0], dp[r-1][t][2]);
                
                // 왼쪽 닫음
                if (t+1 <= k) {
                    long add = arr[r][0];
                    if (dp[r-1][t][0] < INF) dp[r][t+1][1] = Math.min(dp[r][t+1][1], dp[r-1][t][0] + add);
                    if (dp[r-1][t][1] < INF) dp[r][t+1][1] = Math.min(dp[r][t+1][1], dp[r-1][t][1] + add);
                }
                
                if (t+1 <= k) {
                    long add = arr[r][1];
                    if (dp[r-1][t][0] < INF) dp[r][t+1][2] = Math.min(dp[r][t+1][2], dp[r-1][t][0] + add);
                    if (dp[r-1][t][2] < INF) dp[r][t+1][2] = Math.min(dp[r][t+1][2], dp[r-1][t][2] + add);
                }
            }
        }
        
        long ans = sum - Math.min(dp[n][k][0], Math.min(dp[n][k][1], dp[n][k][2]));

        System.out.print(ans);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}
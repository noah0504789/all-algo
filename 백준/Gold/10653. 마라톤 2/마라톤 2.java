import java.io.*;
import java.util.*;

public class Main {
        
    private static int n, k, INF = Integer.MAX_VALUE;
    private static long ans;
    private static long[] left, right;
    private static int[][] checkPoint;
    private static long[][] dp; // 1번에서 i번까지 s개를 건너뛰어 갈 때 최소거리
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        checkPoint = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            checkPoint[i][0] = readInt();
            checkPoint[i][1] = readInt();
        }                
        
        dp = new long[n+1][k+1];
        for (int i = 1; i <= n; i++) Arrays.fill(dp[i], INF);
        dp[1][0] = 0;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int skipCnt = i-j-1;
                for (int s = skipCnt; s <= k; s++) {
                    long prev = dp[j][s-skipCnt];
                    if (prev == INF) continue;
                    dp[i][s] = Math.min(dp[i][s], prev + dist(j, i));
                }
            }
        }
        
        ans = INF;
        for (int s = 0; s <= k; s++) ans = Math.min(ans, dp[n][s]);

        System.out.print(ans);
    }
    
    private static long dist(int i, int j) {
        return Math.abs(checkPoint[i][0] - checkPoint[j][0]) + Math.abs(checkPoint[i][1] - checkPoint[j][1]);
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
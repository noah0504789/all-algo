import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static long max;
    private static int[] road_time, road_reword, bicycle_time, bicycle_reword;
    private static long[][] dp; // 거친 도시, 걸린 시간
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        road_time = new int[n];
        road_reword = new int[n];
        bicycle_time = new int[n];
        bicycle_reword = new int[n];
        
        for (int i = 0; i < n; i++) {
            road_time[i] = readInt();
            road_reword[i] = readInt();
            bicycle_time[i] = readInt();
            bicycle_reword[i] = readInt();
        }
        
        dp = new long[n][k+1];
        for (int i= 0; i < n; i++) Arrays.fill(dp[i], -1);
        
        if (road_time[0] <= k) dp[0][road_time[0]] = road_reword[0];
        if (bicycle_time[0] <= k) dp[0][bicycle_time[0]] = Math.max(dp[0][bicycle_time[0]], bicycle_reword[0]);
        
        for (int i = 1; i < n; i++) {
            int r_time = road_time[i], r_reword = road_reword[i];
            int b_time = bicycle_time[i], b_reword = bicycle_reword[i];
            
            for (int j = 0; j <= k; j++) {
                if (j-r_time>=0 && dp[i-1][j-r_time] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-r_time]+r_reword);
                if (j-b_time>=0 && dp[i-1][j-b_time] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-b_time]+b_reword);
            }
        }
        
        for (int i = 0; i <= k; i++) max = Math.max(max, dp[n-1][i]);

        System.out.print(max);
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
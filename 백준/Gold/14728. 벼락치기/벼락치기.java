import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;
    private static int[][] dp;
    private static int[] times, points;
    private static int n, t, k, s;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        t = readInt();
        
        times = new int[n+1];
        points = new int[n+1];
        
        for (int i = 1; i <= n; i++) {
            times[i] = readInt();
            points[i] = readInt();
        }
        
        dp = new int[n+1][t+1];
        
        for (int i = 1; i <= n; i++) {
            int time = times[i], point = points[i];
            
            for (int j = 1; j <= t; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                
                if (j >= time) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-time]+point);
            }
        }
        
        bw.write(dp[n][t]+"");

        bw.flush();
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}

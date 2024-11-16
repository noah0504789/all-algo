import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[][] dp;
    private static int[] days, pages;
    private static int n, m;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        m = readInt();
        
        days = new int[m+1];
        pages = new int[m+1];
        
        for (int i = 1; i <= m; i++) {
            days[i] = readInt();
            pages[i] = readInt();
        }
        
        dp = new int[m+1][n+1];
        
        for (int i = 1; i <= m; i++) {
            int day = days[i], page = pages[i];
            
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                
                if (j >= day) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-day] + page);
            }
        }
        
        bw.write(dp[m][n]+"");

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

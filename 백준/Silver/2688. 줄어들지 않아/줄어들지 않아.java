import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, maxN;
    private static int[] arr; 
    private static long sum;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        arr = new int[tc];
        for (int i = 0; i < tc; i++) {arr[i] = readInt(); maxN = Math.max(maxN, arr[i]);}
        
        dp = new long[maxN+1][11];
        for (int i = 0; i < 10; i++) dp[1][i] = 1;
        dp[1][10] = 10;
                    
        for (int i = 2; i <= maxN; i++) {                 
            long sum = 0;
            for (int j = 0; j < 10; j++) {
                if (j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i][j-1] + dp[i-1][j];
                sum += dp[i][j];
            }
            dp[i][10] = sum;
        }
                
        for (int v : arr) sb.append(dp[v][10]+"\n");

        System.out.print(sb);
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
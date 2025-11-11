import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, maxN;
    private static int[] arr;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        arr = new int[tc];
        for (int i = 0; i < tc; i++) {
            arr[i] = readInt();
            maxN = Math.max(maxN, arr[i]);
        }
        
        dp = new int[Math.max(3, maxN)+1][4];
        dp[1][1] = 1;
        dp[2][1] = dp[2][2] = 1;
        dp[3][1] = dp[3][2] = dp[3][3] = 1;
        
        for (int i = 4; i <= maxN; i++) {
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }
        
        for (int n : arr) {
            int ans = dp[n][1] + dp[n][2] + dp[n][3];
            sb.append(ans+"\n");
        }

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
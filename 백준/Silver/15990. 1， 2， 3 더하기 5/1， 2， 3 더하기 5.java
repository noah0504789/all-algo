import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, maxN, p = 1_000_000_009;
    private static int[] qs;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        
        qs = new int[tc];
        for (int i = 0; i < tc; i++) {
            qs[i] = readInt();
            if (qs[i] > maxN) maxN = qs[i];
        }
        
        dp = new long[Math.max(3, maxN)+1][4];
        dp[1][1] = 1L;
        dp[2][2] = 1L;            
        dp[3][1] = dp[3][2] = dp[3][3] = 1L;

        for (int i = 4; i <= maxN; i++) {             
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % p;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % p;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % p;
        }
        
        for (int i = 0; i < tc; i++) {             
            n = qs[i];
            
            sb.append((dp[n][1] + dp[n][2] + dp[n][3]) % p).append("\n");
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
import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, maxN, p = 1_000_000_009;
    private static int[] tArr;
    private static long[][] dp; // 숫자,홀짝(0-홀,1-짝)
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {tArr[i] = readInt(); maxN = Math.max(maxN, tArr[i]);}
        
        dp = new long[maxN+1][2];
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[3][0] = 2;
        dp[3][1] = 2;
        
        for (int i = 4; i <= maxN; i++) {
            dp[i][0] = (dp[i-1][1] + dp[i-2][1] + dp[i-3][1]) % p;
            dp[i][1] = (dp[i-1][0] + dp[i-2][0] + dp[i-3][0]) % p;
        }
        
        for (int t : tArr) sb.append(dp[t][0] + " " + dp[t][1]).append("\n");

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
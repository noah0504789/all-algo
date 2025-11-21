import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb = new StringBuilder();
    private static int tc, n, maxN, p = 1_234_567;
    private static int[] tArr;
    private static int[][] dp, adj = {
        {7},
        {2, 4},
        {1, 3, 5},
        {2, 6},
        {1, 5, 7},
        {2,4,6,8},
        {3,5,9},
        {4,8,0},
        {5,7,9},
        {6,8},
    };
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            tArr[i] = readInt();
            maxN = Math.max(maxN, tArr[i]);
        }
        
        dp = new int[maxN+1][10];
        for (int d = 0; d < 10; d++) dp[1][d] = 1;
        for (int i = 1; i < maxN; i++) {
            for (int d= 0; d <10; d++) {
                for (int nxt : adj[d]) {
                    dp[i+1][nxt] += dp[i][d];
                    dp[i+1][nxt] %= p;
                }                
            }
        }
        
        for (int t : tArr) {
            int sum = 0;
            for (int d = 0; d < 10; d++) {
                sum += dp[t][d];
                sum %= p;
            }
            sb.append(sum+"\n");
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
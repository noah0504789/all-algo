import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, m, maxN, p = 1_234_567;
    private static int[] tArr;
     // i, 좌표
    private static int[][] dp, adj = {
        {7},
        {2, 4},
        {1, 3, 5},
        {2, 6},
        {1, 5, 7},
        {2, 4, 6, 8},
        {3, 5, 9},
        {4, 8, 0},
        {5, 7, 9},
        {6,8}
    };
    private static BitSet vis;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        tArr = new int[tc];
        for (int i = 0; i < tc; i++) {
            tArr[i] = readInt();
            maxN = Math.max(maxN, tArr[i]);
        }
        
        n = 4;
        m = 3;
        dp = new int[maxN+1][10];
        vis = new BitSet();
        for (int i = 0; i < 10; i++) dp[1][i] = 1;
        
        for (int len = 1; len < maxN; len++) {
            for (int cur = 0; cur < 10; cur++) {
                long val = dp[len][cur];
                if (val == 0) continue;
                for (int nxt : adj[cur]) {
                    dp[len+1][nxt] += val;
                    dp[len+1][nxt] %= p;
                }
            }
        }
        for (int t : tArr) {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += dp[t][i];
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
import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, pasta, p = 10_000;
    private static long ans;
    private static boolean[][] decided;
    private static int[][] others = {
        {},
        {2, 3},
        {1, 3},
        {1, 2}
    }; 
    private static long[][][] dp; // i일까지/i일에 j종류 파스타를 k번 연속 먹을때 계획수
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        decided = new boolean[n+1][4];
        for (int i = 0; i < k; i++) decided[readInt()][readInt()] = true;
        
        dp = new long[n+1][4][3];
        if (isDecided(1)) dp[1][getPasta(1)][1] = 1;
        else dp[1][1][1] = dp[1][2][1] = dp[1][3][1] = 1;
        
        for (int d = 2; d <= n; d++) {
            if (isDecided(d)) {
                int cur = getPasta(d);
                int[] o = others[cur];
                int o1 = o[0], o2 = o[1];
                
                long a = (dp[d-1][o1][1] + dp[d-1][o1][2]) % p;
                long b = (dp[d-1][o2][1] + dp[d-1][o2][2]) % p;
                
                dp[d][cur][1] = (a + b) % p;
                
                dp[d][cur][2] = dp[d-1][cur][1] % p;
                
                continue;
            }
            
            for (int cur = 1; cur <= 3; cur++) {
                int[] o = others[cur];
                int o1 = o[0], o2 = o[1];
                
                long a = (dp[d-1][o1][1] + dp[d-1][o1][2]) % p;
                long b = (dp[d-1][o2][1] + dp[d-1][o2][2]) % p;
                
                dp[d][cur][1] = (a + b) % p;
                
                dp[d][cur][2] = dp[d-1][cur][1] % p;
            }
        }
        
        if (isDecided(n)) {
            int cur = getPasta(n);
            ans = (dp[n][cur][1] + dp[n][cur][2]) % p;
        } else {
            ans = (ans + dp[n][1][1] + dp[n][1][2]) % p;
            ans = (ans + dp[n][2][1] + dp[n][2][2]) % p;
            ans = (ans + dp[n][3][1] + dp[n][3][2]) % p;
        }
        
        System.out.print(ans);
    }
    
    private static boolean isDecided(int day) {
        return decided[day][1] || decided[day][2] || decided[day][3];
    }
    
    private static int getPasta(int day) {
        if (decided[day][1]) return 1;
        if (decided[day][2]) return 2;
        
        return 3;
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
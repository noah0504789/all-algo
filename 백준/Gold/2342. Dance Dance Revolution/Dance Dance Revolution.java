import java.io.*;
import java.util.*;

public class Main {
    
    private static int v, n, cur, last, ans, INF = Integer.MAX_VALUE;
    private static List<Integer> list;
    private static int[][][] dp; //스테이지,왼,오
    
    public static void main(String... args) throws IOException {
        list = new ArrayList<>();
        
        while ((v=readInt())!=0) list.add(v);
        
        n = list.size();
        dp = new int[n+1][5][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 5; j++) Arrays.fill(dp[i][j], INF);
        }
        dp[0][0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            cur = list.get(i);
            
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (dp[i][j][k] == INF) continue;
                    
                    dp[i+1][cur][k] = Math.min(dp[i+1][cur][k], dp[i][j][k] + point(j, cur));
                    dp[i+1][j][cur] = Math.min(dp[i+1][j][cur], dp[i][j][k] + point(k, cur));
                }
            }
        }
        
        ans = INF;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) ans = Math.min(ans, dp[n][i][j]);
        }
        
        System.out.print(ans);
    }
    
    private static int point(int prev, int cur) {
        if (prev == cur) return 1;
        if (prev == 0) return 2;
        if ((prev+cur)%2 == 0) return 4;
        
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
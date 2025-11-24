import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static long INF = Long.MAX_VALUE / 4;
    private static int[] names, ps;
    private static long[] dp; // i번째 이름 시작 시, 남은 빈칸의 제곱의 최소합
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        names = new int[n+1];
        ps = new int[n+1];
        for (int i = 1; i <= n; i++) {
            ps[i] = names[i] = readInt();
            ps[i] += ps[i-1];
        }
        
        dp = new long[n+2];
        Arrays.fill(dp, -1);
        
        System.out.print(dfs(1));
    }
    
    private static long dfs(int i) {
        if (i == n) return 0;          
        if (dp[i] != -1) return dp[i];
        
        long min = INF;
        for (int ni = i; ni <= n; ni++) {            
            int cnt = ni - i + 1;
            int sum = ps[ni] - ps[i - 1];
            int spaces = cnt - 1;
            int len = sum + spaces;
            
            if (len > m) break;       
            
            if (ni == n) {
                min = 0;
                break;
            }
            
            int blank = m - len;
            min = Math.min(min, (long) blank * blank + dfs(ni + 1));
        }
        
        return dp[i] = min;
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

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int a, b, c, n, m, INF = Integer.MAX_VALUE;
    private static int[] input;
    private static char[] x, y;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        a = input[0];
        b = input[1];
        c = input[2];
        
        x = br.readLine().toCharArray();
        y = br.readLine().toCharArray();
        
        n = x.length;
        m = y.length;
        
        dp = new long[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -INF);
        
        System.out.print(dfs(0, 0));
    }
    
    private static long dfs(int xi, int yi) {
        if (xi == n && yi == m) return 0;
        if (xi == n) return b * (m-yi);
        if (yi == m) return b * (n-xi);
        if (dp[xi][yi] != -INF) return dp[xi][yi];
        
        long max = -INF;
        if (x[xi] == y[yi]) max = Math.max(max, a + dfs(xi+1, yi+1));
        else if (x[xi] != y[yi]) max = Math.max(max, c + dfs(xi+1, yi+1));
        max = Math.max(max, b + dfs(xi, yi+1));
        max = Math.max(max, b + dfs(xi+1, yi));
            
        return dp[xi][yi] = max;
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
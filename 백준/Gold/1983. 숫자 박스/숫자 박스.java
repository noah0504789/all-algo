import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, v, x_len, y_len, INF = Integer.MAX_VALUE;
    private static List<Integer> x, y;
    private static long[][][] dp; // xi, yi 시작 시, 목적지까지 최댓값
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        x = new ArrayList<>();
        y = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            v = readInt();
            if (v != 0) x.add(v);
        }
        
        for (int i = 0; i < n; i++) {
            v = readInt();
            if (v != 0) y.add(v);
        }
        
        x_len = x.size();
        y_len = y.size();
        
        dp = new long[400][400][400];
        for (int i = 0; i < 400; i++) 
            for (int j = 0; j < 400; j++) Arrays.fill(dp[i][j], -INF);

        System.out.print(dfs(0, 0, 0));
    }
    
    private static long dfs(int xi, int yi, int b) {
        if (xi == x_len || yi == y_len) return 0;
        if (dp[xi][yi][b] != -INF) return dp[xi][yi][b];
        
        long max = -INF;
        max = Math.max(max, x.get(xi) * y.get(yi) + dfs(xi+1, yi+1, b+1));
        if (y_len-yi+b<n) max = Math.max(max, dfs(xi+1, yi, b+1));
        if (x_len-xi+b<n) max = Math.max(max, dfs(xi, yi+1, b+1));        
        
        return dp[xi][yi][b] = max;
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
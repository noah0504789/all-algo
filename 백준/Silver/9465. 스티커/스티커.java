import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int tc, n;
    private static int[][] arr;
    private static long[][][] dp;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        while(tc-- > 0) {
            n = readInt();
            arr = new int[2][n];
            for (int i = 0; i < n; i++) arr[0][i] = readInt();            
            for (int i = 0; i < n; i++) arr[1][i] = readInt();
            
            if (n == 1) {
                //sb.append(Math.max(arr[0][0], arr[0][1])).append("\n");    
                //continue;
            }
            
            if (n == 2) {
                //sb.append(Math.max(arr[0][0] + arr[1][1], arr[0][1] + arr[1][0])).append("\n");    
                //continue;
            }
            
            dp = new long[2][n][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) Arrays.fill(dp[i][j], -1);
            }
            
            sb.append(dfs(0, 0, 1)).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static long dfs(int r, int c, int verOK) {
        if (c == n) return 0;
        if (dp[r][c][verOK] != -1) return dp[r][c][verOK];
        
        long max = 0;
        max = Math.max(max, dfs(r, c+1, 1));
        // 현재 점에서 먹기
        max = Math.max(max, arr[r][c] + dfs((r+1)%2, c+1, 0));
        // 아래 점에서 먹기
        if (verOK == 1) max = Math.max(max, arr[(r+1)%2][c] + dfs(r, c+1, 0));
        
        return dp[r][c][verOK] = max;                       
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
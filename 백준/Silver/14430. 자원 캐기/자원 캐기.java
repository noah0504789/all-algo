import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static int[][] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        arr = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) arr[i][j] = readInt();
        }
        
        dp = new int[n+1][m+1]; // 0, 0에서 시작해서 도착지까지 경로에서 얻을 수 있는 최대 광석 개수
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {                
                dp[r][c] = Math.max(dp[r][c-1], dp[r-1][c]) + (arr[r][c] == 1 ? 1 : 0);
            }
        }        

        System.out.print(dp[n][m]);
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
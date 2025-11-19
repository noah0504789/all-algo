import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, min, INF = Integer.MAX_VALUE;
    private static int[][] arr, dir = {
        {1, -1}, {1, 0}, {1, 1} // 왼쪽대각, 아래, 오른쪽대각
    };
    private static int[][][] dp;
    private static Queue<int[]> queue;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n][m];
        dp = new int[n][m][3];
        queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = readInt();                
                if (i == 0) {
                    dp[0][j][0] = arr[0][j];
                    dp[0][j][1] = arr[0][j];
                    dp[0][j][2] = arr[0][j];
                } else {
                    Arrays.fill(dp[i][j], INF);
                }
            }
        }        
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i-1>=0 && j+1<m) dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + arr[i][j];
                if (i-1>=0) dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + arr[i][j];
                if (i-1>=0 && j-1>=0) dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + arr[i][j];                
            }    
        }
            
        min = INF;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, dp[n-1][j][0]);
            min = Math.min(min, dp[n-1][j][1]);
            min = Math.min(min, dp[n-1][j][2]);
        }

        System.out.print(min);
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
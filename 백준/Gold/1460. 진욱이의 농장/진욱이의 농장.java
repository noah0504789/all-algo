import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, ans;
    private static int[][] board, dp;
        
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        board = new int[n][n];
        for (int i_ = 0; i_ < m; i_++) {
            int c = readInt();
            int r = readInt();
            int l = readInt();
            int f = readInt();
            
            //if (f == 0) continue;
            
            for (int i = r; i < r+l; i++) {
                for (int j = c; j < c+l; j++) board[i][j] = f;
            }
        }
                
        for (int a = 1; a <= 7; a++) {
            for (int b = a; b <= 7; b++) {
                //for (int i = 0; i < n; i++) Arrays.fill(dp[i], 0);
                dp = new int[n][n];
                
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int f = board[i][j];                        
                        if (f != a && f != b) {
                            dp[i][j] = 0;
                        } else {
                            int up = i-1>=0 ? dp[i-1][j]: 0;
                            int left = j-1>=0 ? dp[i][j-1]:0;
                            int ul = i-1>=0 && j-1>=0 ? dp[i-1][j-1] : 0;
                            dp[i][j] = Math.min(up, Math.min(left, ul)) + 1;
                            ans = Math.max(ans, dp[i][j]);
                        }
                    }
                }
            }
        }

        System.out.print(ans*ans);
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
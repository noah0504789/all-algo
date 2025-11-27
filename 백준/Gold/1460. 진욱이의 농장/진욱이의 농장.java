import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, x, y, l, f, max, ans;
    private static int[][] arr, dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n][n];
        for (int m_ = 0; m_ < m; m_++) {
            x = readInt();
            y = readInt();
            l = readInt();
            f = readInt();
            
            for (int r = y; r < y + l; r++) {
                for (int c = x; c < x + l; c++) arr[r][c] = f;
            }
        }
        
        for (int a = 1; a <= 7; a++) {
            for (int b = 1; b <= 7; b++) {
                dp = new int[n][n];
                
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        f = arr[r][c];
                        
                        if (f != a && f != b) {
                            dp[r][c] = 0;
                        } else {
                            int up = r-1>=0 ? dp[r-1][c] : 0;
                            int left = c-1>=0 ? dp[r][c-1] : 0;
                            int upLeft = r-1>=0 && c-1>=0 ? dp[r-1][c-1] : 0;
                            
                            dp[r][c] = Math.min(up, Math.min(left, upLeft)) + 1;
                            max = Math.max(max, dp[r][c]);
                        }
                    }
                }
            }
        }

        System.out.print(max * max);
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
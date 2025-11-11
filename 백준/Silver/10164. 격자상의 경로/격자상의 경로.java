import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, k, r, c;
    private static long[][] dp1, dp2;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt()-1;
        if (k == -1) {
            dp1 = new long[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 && j == 0) {
                        dp1[i][j] = 1;
                        continue;
                    }
                    long l = i-1 >= 0 ? dp1[i-1][j] : 0;
                    long r = j-1 >= 0 ? dp1[i][j-1] : 0;
                    dp1[i][j] = l+r;
                }
            }
            
            System.out.print(dp1[n-1][m-1]);
            return;
        }
        
        r = k / m;
        c = k % m;
        
        dp1 = new long[r+1][c+1];
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                if (i == 0 && j == 0) {
                    dp1[i][j] = 1;
                    continue;
                }
                long l = i-1 >= 0 ? dp1[i-1][j] : 0;
                long r = j-1 >= 0 ? dp1[i][j-1] : 0;
                dp1[i][j] = l+r;
            }
        }
        
        int h = n-r;
        int w = m-c;
        dp2 = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 && j == 0) {
                    dp2[i][j] = 1;
                    continue;
                }
                long l = i-1 >= 0 ? dp2[i-1][j] : 0;
                long r = j-1 >= 0 ? dp2[i][j-1] : 0;
                dp2[i][j] = l+r;
            }
        }

        System.out.print(dp1[r][c] * dp2[h-1][w-1]);
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
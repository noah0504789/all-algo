import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int tc, n, m;
    private static long[][] psum, dp; // i개를 뽑을 때, 현재 값이 j일 때, 경우의 수
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();
        while (tc-->0) {
            n = readInt();
            m = readInt();
            
            dp = new long[n+1][m+1];
            psum = new long[n+1][m+1];
            for (int i = 1; i <= m; i++) {
                dp[1][i] = 1;
                psum[1][i] = psum[1][i-1]+1;
            }
            
            for (int i = 2; i <= n; i++) {                
                for (int j = 1; j <= m; j++) {
                    int half = j/2;
                    dp[i][j] = psum[i-1][half];
                    psum[i][j] = psum[i][j-1] + dp[i][j];
                }
            }
                        
            sb.append(psum[n][m]+"\n");
        }

        System.out.print(sb);
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
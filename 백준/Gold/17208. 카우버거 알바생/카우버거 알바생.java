import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m, k, ans;
    private static int[] cheese, potato;
    private static int[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        k = readInt();
        
        cheese = new int[n];
        potato = new int[n];
        for (int i = 0; i < n; i++) {
            cheese[i] = readInt();
            potato[i] = readInt();
        }
        
        dp = new int[m+1][k+1];                
        for (int i = 0; i < n; i++) {
            int c = cheese[i], p = potato[i];
            
            for (int j = m; j >= c; j--) {
                for (int l = k; l >= p; l--) {
                    dp[j][l] = Math.max(dp[j][l], dp[j-c][l-p] + 1);
                }
            }
        }
        
        System.out.print(dp[m][k]);
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
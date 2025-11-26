import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, max;
    private static int[][] arr, dp; // (0, 0) -> (i, j) 까지 부분합
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        while ((n=readInt())!=0 && (m=readInt())!=0) {
            arr = new int[n+1][m+1];
            dp = new int[n+1][m+1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    arr[i][j] = readInt();
                    dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + arr[i][j];
                }
            }
            
            max = 0;
            
            for (int len = 1; len <= Math.min(n, m); len++) {
                for (int sr = 1; sr <= n; sr++) {                    
                    boolean flag = false;
                    int dr = sr + len -1;
                    if (dr > n) break;        
                    
                    for (int sc = 1; sc <= m; sc++) { 
                        int dc = sc + len -1;
                        if (dc > m) break;
                        
                        if (len*len==val(sr, sc, dr, dc)) {
                            max = len;
                            flag = true;
                            break;
                        }
                    }
                    
                    if (flag) break;
                }
            }
            
            sb.append(max+"\n");
        }

        System.out.print(sb);
    }
    
    private static int val(int sr, int sc, int dr, int dc) {
        return dp[dr][dc] - dp[dr][sc-1] - dp[sr-1][dc] + dp[sr-1][sc-1];
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
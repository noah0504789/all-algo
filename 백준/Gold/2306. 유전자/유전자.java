import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n;
    private static char[] arr;
    private static int[][] dp; // l, r: 구간별 KOI 문자열 최대값
    
    public static void main(String... args) throws IOException {
        arr = br.readLine().toCharArray();
        n = arr.length;
        dp = new int[n][n];
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i +len -1 < n; i++) {
                int j = i+len-1;
                
                dp[i][j] = dp[i+1][j];                
                for (int k = i; k <= j; k++) {
                    if (!isPair(arr[i], arr[k])) continue;
                    int inside = k-1>=0 ? dp[i+1][k-1] : 0;
                    int right = k+1 < n ? dp[k+1][j] : 0;
                    
                    dp[i][j] = Math.max(dp[i][j], inside+2+right);
                }
            }
        }

        System.out.print(dp[0][n-1]);
    }
    
    private static boolean isPair(char a, char b) {
        return (a=='a' && b=='t') || (a=='g'&&b=='c');
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
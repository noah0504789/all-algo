import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, p = 1_000_000_003;
    private static int[][] dp; // n개, k개, 0-첫번째에 두기/1-두번쨰에 두기
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        dp = new int[n+1][k+1];
        for (int i = 1; i <= n; i++) dp[i][0] = 1;
        dp[1][1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i][1] = i;
            for (int j = 2; j <= k; j++) {
                dp[i][j] += dp[i-1][j] + dp[i-2][j-1];
                dp[i][j] %= p;
            }
        }
        
        int choose_first = n-3>=0 && k-1>= 0 ? dp[n-3][k-1] : 0;
        int choose_second = n-1>=0? dp[n-1][k] : 0;

        System.out.print((choose_first + choose_second) % p);
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
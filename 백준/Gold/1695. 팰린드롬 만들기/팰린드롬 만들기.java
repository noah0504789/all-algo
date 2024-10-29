import java.io.*;
import java.util.*;

public class Main {
    private static int[][] dp;
    private static int[] nums;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();
        
        nums = new int[n];        
        for (int i = 0; i < n; i++) nums[i] = readInt();
        
        dp = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                
                if (nums[i] == nums[j]) dp[i][j] = dp[i+1][j-1];
                else dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
            }    
        }        

        System.out.print(dp[0][n-1]);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}

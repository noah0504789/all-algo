import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    private static boolean[][] dp;
    private static int[] nums;    
    private static int n, m, s, e, result;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        nums = new int[n];
        dp = new boolean[n][n];
        
        for (int i = 0; i < n; i++) nums[i] = readInt();
        
        for (int i = 0; i < n; i++) dp[i][i] = true;
        for (int i = 0; i < n-1; i++) {
            if (nums[i] == nums[i+1]) dp[i][i+1] = true;
        }
        
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i < n-length; i++) {
                int j = i+length;
                if (nums[i] == nums[j] && dp[i+1][j-1]) dp[i][j] = true;
            }
        }
        
        m = readInt();
        
        for (int i = 0; i < m; i++) {
            s = readInt()-1;
            e = readInt()-1;
            
            sb.append(dp[s][e] ? 1 : 0).append('\n');
        }        
        
        System.out.print(sb);
    }
    
    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();
        
        while (c <= ' ') c = System.in.read();
        
        while (c >= '0' && c <= '9') {
            result *= 10;
            result += (c - '0');
            c = System.in.read();
        }
        
        return result;
    }
}
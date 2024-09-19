import java.io.*;

public class Main {
    private static long[][] dp;
    private static int[] vol, value;
    private static int n, k;

    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        vol = new int[n+1];
        value = new int[n+1];
        
        dp = new long[n+1][k+1];
        
        for (int i = 1; i <= n; i++) {
            vol[i] = readInt();
            value[i] = readInt();
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int curVol = vol[i], curValue = value[i];
                
                if (curVol <= j) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-curVol]+curValue);
                else dp[i][j] = dp[i-1][j];
            }    
        }
                
        System.out.print(dp[n][k]);
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        return result;
    }
}

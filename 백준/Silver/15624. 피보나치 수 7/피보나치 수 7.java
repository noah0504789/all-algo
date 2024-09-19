import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_007;
    private static int[] dp;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();
        
        if (n == 0) {
            System.out.print(0);
            return;
        } else if (n <= 2) {
            System.out.print(1);
            return;
        }
        
        dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        
        for (int i = 3; i <= n; i++) dp[n] = (dp[n-1] + dp[n-2]) % MOD;

        System.out.print(dp[n]);
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

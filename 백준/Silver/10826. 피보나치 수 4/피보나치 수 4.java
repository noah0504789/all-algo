import java.io.*;
import java.math.*;

public class Main {
    private static BigInteger[] dp;
    private static int n;

    public static void main(String... args) throws IOException {
        n = readInt();
                
        dp = new BigInteger[n+1];
        
        System.out.print(fibo(n));
    }
    
    private static BigInteger fibo(int n) {
        if (n == 0) return dp[n] = BigInteger.ZERO;
        else if (n <= 2) return dp[n] = BigInteger.ONE;        
        else if (dp[n] != null) return dp[n];
        
        return dp[n] = fibo(n-1).add(fibo(n-2));
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

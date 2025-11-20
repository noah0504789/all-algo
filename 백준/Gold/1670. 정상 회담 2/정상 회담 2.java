import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, pairs, p = 987654321;
    private static long sum;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        pairs = n/2;
        dp = new long[pairs+1];
        dp[0] = 1;
        for (int i = 1; i <= pairs; i++) {
            sum = 0;
            for (int l = 0; l < i; l++) {
                int r = i-1-l;
                sum += (dp[l] * dp[r]) % p;
                sum %= p;
            }
            dp[i] = sum;
        }

        System.out.print(dp[pairs] % p);
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
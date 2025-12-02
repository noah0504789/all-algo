import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] cards;
    private static long[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        cards = new int[n+1];
        for (int i = 1; i <= n; i++) cards[i] = readInt();
        
        dp = new long[n+1];
        for (int i = 1; i <= n; i++) {
            int card = cards[i];
            for (int j = i; j <= n; j++) dp[j] = Math.max(dp[j], dp[j-i] + card);
        }
        
        System.out.print(dp[n]);
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
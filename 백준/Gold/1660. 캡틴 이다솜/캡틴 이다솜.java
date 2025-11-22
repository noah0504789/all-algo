import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, t, cnt, INF = Integer.MAX_VALUE;
    private static List<Integer> tetra;
    private static int[] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        tetra = new ArrayList<>();
        k = 1;        
        while (true) {
            t = k * (k+1) * (k+2) / 6;
            if (t > n) break;
            tetra.add(t);
            k++;
        }
        
        dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int t_ : tetra) {
            for (int i = t_; i <= n; i++) {
                dp[i] = Math.min(dp[i], dp[i-t_]+1);
            }
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
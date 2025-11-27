import java.io.*;
import java.util.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    private static String input;
    private static int n, s, ans, p = 1_000_000_007;
    private static int[] dp, prev, ps, tmp;
    
    public static void main(String... args) throws IOException {
        s = Integer.parseInt(br.readLine());
        input = br.readLine();
        n = input.length();
        
        dp = new int[s+1];
        prev = new int[s+1];
        ps = new int[s+1];
        
        prev[0] = 1;
        
        for (int i = 0; i < n; i++) {
            ps[0] = prev[0];
            for (int t = 1; t <= s; t++) ps[t] = (ps[t-1] + prev[t]) % p;            
            for (int t = 0; t <= s; t++) dp[t] = (ps[t] - (t-26 >= 0 ? ps[t-26] : 0) + p) % p;
            
            tmp = prev;
            prev = dp;
            dp = tmp;
        }

        System.out.print(prev[s] % p);
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
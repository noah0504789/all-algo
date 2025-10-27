import java.io.*;
import java.util.*;

public class Main {
    
    private static final int MOD = 1_000_000_007;
    private static StringBuilder sb;
    private static int tc, x, k;
    private static long ans;
    
    public static void main(String... args) throws IOException {
        tc = readInt();
        ans = 0;
        while (tc-- > 0) {
            x = readInt();
            k = readInt();
            
            long term = 1;
            while (k > 0) {
                if ((k&1) != 0) {
                    ans += term;
                    ans %= MOD;
                }
                term *= x;
                term %= MOD;
                k>>=1;
            }
        }
        
        System.out.print(ans % MOD);
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
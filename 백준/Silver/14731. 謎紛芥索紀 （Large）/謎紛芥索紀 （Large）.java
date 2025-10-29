import java.io.*;
import java.util.*;

public class Main {
    
    private static final int MOD = 1_000_000_007;
    private static long n, ans;
    
    public static void main(String... args) throws IOException {
        n = readLong();
        
        ans = 0L;
        for (int i = 0; i < n; i++) {
            long a = readLong();
            long b = readLong();
            
            if (b == 0) continue;

            long res = pow(2, b-1);
            long c = (a*b) % MOD;
            
            res *= c;
            res %= MOD;
            
            ans += res;
            ans %= MOD;
        }

        System.out.print(ans);
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b&1)!=0) {
                res *= a;
                res %= MOD;
            }
            a *= a;
            a %= MOD;
            b >>= 1;
        }
        return res;
    }

    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();
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
import java.io.*;
import java.util.*;

public class Main {
    
    private static final int MOD = 1_000_000_007;
    private static long n, m, ans;
    
    public static void main(String... args) throws IOException {
        n = readLong();
        m = readLong();
        
        if (n == 1 || m == 0) {
            System.out.print(1);
            return;
        }
        
        List<long[]> factors = factorize(n);
        ans = 1;
        
        for (long[] f : factors) {
            long p = f[0], a = f[1];
            long e = a * m + 1;
            
            long x = pow(p % MOD, e) - 1;
            if (x < 0) x += MOD;
            
            long base = (p-1) % MOD;
            if (base < 0) base += MOD;
            
            long y = pow(base, MOD-2);
            if (y < 0) y += MOD;
            
            ans *= x;
            ans %= MOD;
            ans *= y;
            ans %= MOD;
        }

        System.out.print(ans);
    }
    
    private static List<long[]> factorize(long n) {
        List<long[]> res = new ArrayList<>();
        for (long d = 2; d * d <= n; d++) {
            if (n % d != 0) continue;
            
            long cnt = 0;
            while (n % d == 0) {
                n /= d;
                cnt++;
            }
            
            res.add(new long[]{d, cnt});
        }
        
        if (n > 1) res.add(new long[]{n, 1});
        
        return res;
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if((b&1)!=0) {
                res *= a;
                res %= MOD;
            }
            a*=a;
            a%=MOD;
            b>>=1;
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
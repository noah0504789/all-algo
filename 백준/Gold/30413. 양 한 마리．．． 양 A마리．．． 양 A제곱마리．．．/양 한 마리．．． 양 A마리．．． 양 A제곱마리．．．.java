import java.io.*;
import java.util.*;

public class Main {
    
    private static final int MOD = 1_000_000_007;
    private static long a, b;
    
    public static void main(String... args) throws IOException {
        a = readLong();
        b = readLong();
        
        if (a == 1) {
            System.out.print(b % MOD);
            return;
        }
        
        // (a^b-1/a-1) mod p
        // == a^b-1 mod p * (a-1)^-1 mod p
        
        // gcd(a-1, p) = 1
        // (a-1) mod p == (a-1)^(p-1) mod p
        // (a-1)^-1 mod p == (a-1)^(p-2) mod p
        
        // -> ((pow(a, b) - 1) * pow(a-1, p-2)) mod p
        // -> (((pow(a, b) - 1 + p) mod p) * pow(a-1, p-2)) mod p
        
        long x = (pow(a, b)-1 + MOD) % MOD;
        long y = pow(((a-1 + MOD) % MOD), MOD-2);
        
        System.out.print((x * y) % MOD);
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
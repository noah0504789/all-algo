import java.io.*;
import java.util.*;

public class Main {
    
    private static long a, b, n;
    private static final int MOD = 1_000_000_007;
    
    public static void main(String... args) throws IOException {
        a = readInt();
        b = readInt();
        n = readInt();

        System.out.print(pow(2L, (long)(31L*(n-1))));
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b&1)==1) {
                res *= a;
                res %= MOD;
            }
            
            a *= a;
            a %= MOD;
            b>>=1;
        }
        return res;
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
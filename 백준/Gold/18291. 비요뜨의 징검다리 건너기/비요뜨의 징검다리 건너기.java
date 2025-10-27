import java.io.*;
import java.util.*;

public class Main {
    
    private static final int MOD = 1_000_000_007;
    private static StringBuilder sb;
    private static int tc, n;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        tc = readInt();                
        while (tc-- > 0) {
            n = readInt();                    
            
            sb.append(powmod(2, n-2));
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    private static long powmod(long a, long b) {        
        long res = 1;
        while (b > 0) {
            if ((b&1)==1) {
                res *= a;
                res %= MOD;
            }
            a*= a;
            a%= MOD;
            b >>= 1;
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
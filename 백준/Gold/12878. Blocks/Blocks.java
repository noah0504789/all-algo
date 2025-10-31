import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 10_007;
    private static long[] pow2;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        long a = pow(2, 2*n-2);
        long b = pow(2, n-1);
        
        System.out.print((a+b) % p);
    }
    
    private static long pow(long a, long b) {
        long res = 1;
        while (b>0) {
            if ((b&1)!=0) {
                res*=a;
                res%=p;                
            }
            a*=a;
            a%=p;
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
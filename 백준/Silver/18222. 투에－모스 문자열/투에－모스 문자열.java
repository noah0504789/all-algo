import java.io.*;
import java.util.*;

public class Main {
    
    private static long k, l;
    
    public static void main(String... args) throws IOException {
        k = readLong();
                
        l = nextPow2(k);
        int flip = 0;
        while (l > 1) {
            long half = l>>1;
            if (k > half) {
                k -= half;
                flip ^= 1;
            }
            l = half;
        }
        
        System.out.print(flip);
    }
    
    private static long nextPow2(long x) {
        long h = Long.highestOneBit(x);
        return (x == h) ? x : (h<<1);
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
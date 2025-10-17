import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String... args) throws IOException {
        System.out.print(lowerBound(readLong()));
    }
    
    private static long lowerBound(long n) {
        if (n == 0) return 0;
        
        long l = 1L, r = 3_037_000_501L;
        while (l < r) {
            long m = (l+r) >>> 1;
                       
            if (m > (n-1)/m) r = m;
            else l = m+1;
        }
        
        return l;
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
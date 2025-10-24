                                                                                                                                                                                                                                                                                                    import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k;
    private static long l, r, m;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
              
        l = 1;
        r = k;
        while (l < r) {
            m = l+((r-l)>>>1);
            if (cal(m) >= k) {
                r = m;
            } else {
                l = m+1;
            }
        }
        
        System.out.print(l);
    }
    
    private static long cal(long t) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            long cnt = t / i;
            if (cnt > n) cnt = n;
            sum += cnt;
        }
        return sum;
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
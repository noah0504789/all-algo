import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, d, l, r, m, min;
    private static int[] first, last, gap;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        d = readInt();
        
        first = new int[k];
        last = new int[k];
        gap = new int[k];    
        min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            first[i] = readInt();
            last[i] = readInt();
            gap[i] = readInt();
            
            min = Math.min(min, first[i]);
        }
        l = min;
        r = 1_000_001;
        
        while (l < r) {
            m = (l+r)>>>1;
            if (can(m)) {
                r = m;
            } else {
                l = m+1;
            }
        }

        System.out.print(l);
    }
    
    private static boolean can(int t) {
        long cnt = 0;        
        for (int i = 0; i < k; i++) {
            int a = first[i], b = last[i], c = gap[i];
            int hi = Math.min(b, t);
            if (hi >= a) cnt += (hi-a)/c+1;            
            
            if (cnt >= d) return true;
        }
        
        return false;
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
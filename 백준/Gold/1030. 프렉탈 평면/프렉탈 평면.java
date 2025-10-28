import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int s, n, k, r1, r2, c1, c2, mid, midEnd, w;    
    private static long[] pow;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        s = readInt();
        n = readInt();
        k = readInt();
        r1 = readInt();
        r2 = readInt();
        c1 = readInt();
        c2 = readInt();
        
        mid = (n-k)/2;
        midEnd = mid+k-1;
        
        pow = new long[s+1];
        pow[0] = 1;
        for (int i = 1; i <= s; i++) pow[i] = pow[i-1] * n;                              
        
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) sb.append(isBlack(i, j) ? '1' : '0');
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    private static boolean isBlack(int r, int c) {
        for (int t = pow.length-1; t >= 0; t--) {
            long block = pow[t];
            int ri = (int)((r/block) % n);
            int ci = (int)((c/block) % n);
            if (ri >= mid && ri <= midEnd && ci >= mid && ci <= midEnd) return true;
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
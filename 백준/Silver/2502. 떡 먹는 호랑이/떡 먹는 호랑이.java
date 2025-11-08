import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int d, k;
    private static long[] f, s;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        d = readInt();
        k = readInt();
        
        f = new long[]{1, 0};
        s = new long[]{0, 1};
        f(2);
        
        for (int a = 1; a <= k; a++) {
            long s0 = s[0], s1=s[1];
            long b = k-(s0*a);
            if (b % s1 == 0) {
                b /= s1;
                sb.append(a).append("\n").append(b);
                break;
            }
        }

        System.out.print(sb);
    }
    
    private static void f(int idx) {
        if (idx == d) return;
        
        long s0 = s[0], s1 = s[1];
        
        s[0]+=f[0];
        s[1]+=f[1];
        
        f[0] = s0;
        f[1] = s1;
        
        f(idx+1);
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
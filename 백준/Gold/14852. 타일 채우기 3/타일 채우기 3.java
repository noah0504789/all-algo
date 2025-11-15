import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, p = 1_000_000_007;
    private static long a0, a1, a2, a;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        if (n == 0) { System.out.print(0); return; }
        if (n == 1) { System.out.print(2); return; }
        if (n == 2) { System.out.print(7); return; }
        
        a0=1;
        a1=2;
        a2=7;
        a=0;
        for (int i = 3; i <= n; i++) {
            a = (3*a2+a1-a0) % p;
            if (a < 0) a += p;
            a0 = a1;
            a1 = a2;
            a2 = a;
        }
        
        System.out.print(a2);
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
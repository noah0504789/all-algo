import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, MOD = 1_000_000_000;
    private static int d0, d1, dn;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        if (n == 0) {
            System.out.print(1);    
            return;
        }
        
        if (n == 1) {
            System.out.print(0);
            return;
        }
        
        long d0 = 1; // !0
        long d1 = 0; // !1
        long dn = 0;

        for (int i = 2; i <= n; i++) {
            dn = ((i - 1L) * ((d1 + d0) % MOD)) % MOD;
            d0 = d1;
            d1 = dn;
        }

        System.out.print(dn % MOD);
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
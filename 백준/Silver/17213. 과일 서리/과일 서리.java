import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static long[][] dp;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        System.out.print(comb(m-1, n-1));
    }
    
    private static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k > n-k) k = n-k;
        long res = 1;
        for (int i = 1; i <= k; i++) res = res * (n-k+i) / i;
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
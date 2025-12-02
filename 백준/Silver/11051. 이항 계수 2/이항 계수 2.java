import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, k, p = 10_007;
    private static long[][] comb;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readInt();
        
        comb = new long[n+1][n+1];
        comb[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j <= k; j++) comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % p;
        }
                
        System.out.print(comb[n][k] % p);
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
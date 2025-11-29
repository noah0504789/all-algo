import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m;
    private static BigInteger[][] comb;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        comb = new BigInteger[n+1][n+1];
        comb[0][0] = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            comb[i][0] = comb[i][i] = new BigInteger("1");
            
            for (int j = 1; j <= m; j++) {
                if (comb[i-1][j-1] == null) comb[i-1][j-1] = new BigInteger("0");
                if (comb[i-1][j] == null) comb[i-1][j] = new BigInteger("0");
                comb[i][j] = comb[i-1][j-1].add(comb[i-1][j]);
            }
        }

        System.out.print(comb[n][m]);
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
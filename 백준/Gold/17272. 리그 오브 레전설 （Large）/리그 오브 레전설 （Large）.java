import java.io.*;
import java.util.*;

public class Main {
    
    private static int m, p = 1_000_000_007;
    private static long n, exp;    
    private static long[] dp, s;
    private static long[][] a;
    
    public static void main(String... args) throws IOException {
        n = readLong();
        m = readInt();
        
        if (n < m) {
            System.out.println(1);
            return;
        }
        
        dp = new long[m+1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) dp[i] = (dp[i-1] + (i-m >= 0 ? dp[i-m] : 0)) % p;
        
        s = new long[m];
        s[0] = dp[m];
        for (int i = 1; i < m; i++) s[i] = dp[m-i];
        
        a = new long[m][m];
        a[0][0] = a[0][m-1] = 1;
        for (int i = 1; i < m; i++) a[i][i-1] = 1;

        System.out.print(mulMatVec(pow(a, n-m), s)[0] % p);
    }
    
    private static long[][] pow(long[][] base, long exp) {
        long[][] res = new long[m][m];
        for (int i = 0; i < m; i++) res[i][i]=1;
        
        while (exp > 0) {
            if ((exp&1)!=0) res = mul(base, res);
            
            base = mul(base, base);
            exp>>=1;
        }
        return res;
    }
    
    private static long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < m; k++) {
                long aik = a[i][k];
                if (aik == 0) continue;
                for (int j = 0; j < m; j++) {
                    long bkj = b[k][j];
                    if (bkj == 0) continue;
                    res[i][j] = (res[i][j] + aik * bkj) % p;
                }
            }    
        }
        
        return res;
    }
    
    private static long[] mulMatVec(long[][] a, long[] b) {
        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            long sum = 0;
            for (int j = 0; j < m; j++) {
                long v = b[j];
                if (a[i][j] == 0 || v == 0) continue;
                sum = (sum + a[i][j] * v) % p;
            }
            res[i] = sum;
        }
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
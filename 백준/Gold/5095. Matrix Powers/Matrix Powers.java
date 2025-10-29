import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n;
    private static long m, p;
    private static long[][] arr, ans;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        while (true) {
            n = readInt();
            m = readLong();
            p = readLong();
            if (n == 0 && m == 0 && p == 0) break;
            
            arr = new long[n][n];                           
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) arr[i][j] = readLong() % m;
            }
            
            ans = identity(n);
            
            pow(p);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j > 0) sb.append(" ");
                    sb.append(ans[i][j]);
                }
                sb.append("\n");
            }
            
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    private static void pow(long b) {        
        while (b > 0) {
            if ((b&1)!=0) ans = mul(ans, arr);
            arr = mul(arr, arr);
            b>>=1;
        }        
    }    
    
    private static long[][] identity(int n) {
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1 % m;
        return res;
    }
    
    private static long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) continue;
                for (int k = 0; k < n; k++) c[i][k] += (a[i][j] * b[j][k]);                
            }
            
            for (int j = 0; j < n; j++) c[i][j] %= m;
        }
        
        return c;
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
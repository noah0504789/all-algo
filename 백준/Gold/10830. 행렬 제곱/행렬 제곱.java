import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, p = 1_000;
    private static long b;    
    private static long[][] arr, res, base;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        b = readLong();
        
        arr = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) arr[i][j] = readLong();
        }
        
        res = pow(arr, b);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) sb.append((res[i][j] % p) + " ");
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    private static long[][] pow(long[][] a, long b) {
        long[][] res = base(n);

        while (b > 0) {
            if ((b&1)!=0) res = mul(res, a);
            
            a = mul(a, a);
            b>>=1;
        }
        
        return res;
    }
    
    private static long[][] base(int n) {
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;
        return res;
    }
    
    private static long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) res[i][j] += (a[i][k] * b[k][j]) % p;
            }
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
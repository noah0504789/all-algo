import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m, k;
    private static int[][] a, b, c;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) a[i][j] = readInt();
        }
                
        m = readInt();
        k = readInt();
        
        b = new int[m][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) b[i][j] = readInt();
        }
        
        c = new int[n][k];
        
        multiply(a, b);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                sb.append(c[i][j]+" ");
            }    
            sb.append("\n");
        }

        System.out.print(sb);
    }
    
    private static void multiply(int[][] a, int[][] b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int v = 0; v < m; v++) {
                    c[i][j] += a[i][v] * b[v][j];
                }    
            }    
        }
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
import java.io.*;
import java.util.*;

public class Main {
    
    private static StringBuilder sb;
    private static int n, m;
    private static int[][] a, b;
    
    public static void main(String... args) throws IOException {
        sb = new StringBuilder();
        
        n = readInt();
        m = readInt();
        
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) a[i][j] = readInt();
        }
        
        n = readInt();
        m = readInt();
        
        b = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) b[i][j] = readInt();
        }
        
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                int val = 0;
                for (int k = 0; k < a[0].length; k++) val += (a[i][k] * b[k][j]);
                sb.append(val+ " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
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
import java.io.*;
import java.util.*;

public class Main {
        
    private static StringBuilder sb = new StringBuilder();
    private static int n, m;
    private static int[][] arr;
    private static long[][] ps;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        
        arr = new int[n+1][n+1];
        ps = new long[n+1][n+1];
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                arr[r][c] = readInt();
                ps[r][c] = ps[r][c-1] + ps[r-1][c] - ps[r-1][c-1] + arr[r][c];
            }
        }
        
        for (int i = 0; i < m; i++) {
            int r1 = readInt();
            int c1 = readInt();
            
            int r2 = readInt();
            int c2 = readInt();            
            
            sb.append(ps[r2][c2] - ps[r1-1][c2] - ps[r2][c1-1] + ps[r1-1][c1-1]).append("\n");
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